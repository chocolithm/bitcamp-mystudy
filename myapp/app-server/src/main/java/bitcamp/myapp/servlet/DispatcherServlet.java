package bitcamp.myapp.servlet;

import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.annotation.RequestParam;
import bitcamp.myapp.context.ApplicationContext;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class DispatcherServlet extends HttpServlet {

  ApplicationContext appCtx;
  private List<Object> controllers;

  public DispatcherServlet(ApplicationContext appCtx) {
    this.appCtx = appCtx;
    this.controllers = appCtx.getControllers();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      String controllerPath = req.getPathInfo();

      Object pageController = null;
      Method requestHandler = null;

      loop:
      for (Object controller : controllers) {
        Method[] methods = controller.getClass().getDeclaredMethods(); // 현재 클래스의 메서드만
        for (Method m : methods) {
          RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
          if (requestMapping == null || !requestMapping.value().equals(controllerPath)) {
            continue;
          }

          requestHandler = m;
          pageController = controller;
          break loop;
        }
      }

      if (pageController == null) {
        throw new Exception("해당 URL의 처리할 수 없습니다.");
      }

      Map<String, Object> map = new HashMap<>();
      Object[] arguments = prepareRequestHandlerArguments(requestHandler, req, res, map);

      if (requestHandler.getReturnType() == void.class) {
        requestHandler.invoke(pageController, arguments);
        return;
      }

      String viewName = (String) requestHandler.invoke(pageController, arguments);

      if (map.size() > 0) {
        copyMapToServletRequest(map, req);
      }

      if (viewName.startsWith("redirect:")) {
        res.sendRedirect(viewName.substring(9));

      } else {
        req.getRequestDispatcher(viewName).forward(req, res);
      }

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }

  private Object[] prepareRequestHandlerArguments(
      Method requestHandler,
      HttpServletRequest req,
      HttpServletResponse res,
      Map<String, Object> requestAttributesMap) throws Exception {
    Parameter[] params = requestHandler.getParameters();
    ArrayList<Object> args = new ArrayList<>();

    for (Parameter param : params) {
      Class<?> paramType = param.getType();
      if (paramType == ServletRequest.class || paramType == HttpServletRequest.class) {
        args.add(req);
      } else if (paramType == ServletResponse.class || paramType == HttpServletResponse.class) {
        args.add(res);
      } else if (paramType == HttpSession.class) {
        args.add(req.getSession());
      } else if (paramType == Map.class) {
        args.add(requestAttributesMap);
      } else if (paramType.isPrimitive()
          || paramType == String.class
          || paramType == java.util.Date.class
          || paramType == java.sql.Date.class
          || paramType == int[].class
      ) {
        RequestParam paramAnno = param.getAnnotation(RequestParam.class);
        args.add(getDefaultTypeValueFromRequestParameter(
            req,
            param.getType(),
            paramAnno.value()));
      } else if (paramType == Part.class) {
        RequestParam paramAnno = param.getAnnotation(RequestParam.class);
        args.add(req.getPart(paramAnno.value()));
      } else if (paramType == Part[].class) {
        RequestParam paramAnno = param.getAnnotation(RequestParam.class);
        args.add(getPartArray(req, paramAnno.value()));
      } else {
        args.add(createDomainObject(req, param.getType()));
      }
    }

    return args.toArray();
  }

  private void copyMapToServletRequest(Map<String, Object> map, ServletRequest req) {
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      req.setAttribute(entry.getKey(), entry.getValue());
    }
  }

  private Object getDefaultTypeValueFromRequestParameter(
      HttpServletRequest req,
      Class<?> paramType,
      String paramName) {

    String paramValue = req.getParameter(paramName);
    if (paramType != boolean.class
        && paramType.getComponentType() == null
        && paramValue == null) {
      return null;
    }

    if (paramType == byte.class) {
      return Byte.parseByte(paramValue);
    } else if (paramType == short.class) {
      return Short.parseShort(paramValue);
    } else if (paramType == int.class) {
      return Integer.parseInt(paramValue);
    } else if (paramType == int[].class) {
      String[] paramValues = req.getParameterValues(paramName);
      if (paramValues == null) {
        return new int[0];
      }

      int[] values = new int[paramValues.length];
      for (int i = 0; i < paramValues.length; i++) {
        values[i] = Integer.parseInt(paramValues[i]);
      }
      return values;
    } else if (paramType == long.class) {
      return Long.parseLong(paramValue);
    } else if (paramType == float.class) {
      return Float.parseFloat(paramValue);
    } else if (paramType == double.class) {
      return Double.parseDouble(paramValue);
    } else if (paramType == char.class) {
      return paramValue.charAt(0);
    } else if (paramType == boolean.class) {
      if (paramValue == null
          || paramValue.equals("0")
          || paramValue.equals("false")
          || paramValue.equals("off")
          || paramValue.equals("no")
      ) {
        return false;
      }

      return true;
    } else if (paramType == java.util.Date.class || paramType == java.sql.Date.class) {
      return java.sql.Date.valueOf(paramValue);
    } else {
      return paramValue;
    }
  }

  private Object createDomainObject(HttpServletRequest req, Class<?> paramType) throws Exception {
    Object domainObject = paramType.getConstructor().newInstance();
    Method[] methods = paramType.getDeclaredMethods();

    for (Method m : methods) {
      if (!Modifier.isPublic(m.getModifiers()) || !m.getName().startsWith("set")) {
        continue;
      }

      Class<?> propertyType = m.getParameterTypes()[0];
      String propertyName = Character.toLowerCase(m.getName().charAt(3)) + m.getName().substring(4);

      Object value = getDefaultTypeValueFromRequestParameter(req, propertyType, propertyName);
      if (value == null) {
        continue;
      }

      m.invoke(domainObject, value);
    }

    return domainObject;
  }

  private Part[] getPartArray(HttpServletRequest req, String paramName) throws Exception {
    Collection<Part> parts = req.getParts();
    ArrayList<Part> list = new ArrayList<>();
    for (Part part : parts) {
      if (!part.getName().equals(paramName)) {
        continue;
      }
      list.add(part);
    }
    return list.toArray(new Part[0]);
  }
}
