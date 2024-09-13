package bitcamp.myapp.servlet;

import bitcamp.myapp.annotation.RequestMapping;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(
    maxFileSize = 1024 * 1024 * 60,
    maxRequestSize = 1024 * 1024 * 100)
@WebServlet("/app/*")
public class FrontDispatcherServlet extends HttpServlet {

  private List<Object> controllers;

  @Override
  public void init() throws ServletException {
    controllers = (List<Object>) this.getServletContext().getAttribute("controllers");
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

      if (requestHandler.getReturnType() == void.class) {
        requestHandler.invoke(pageController, req, res);
        return;
      }

      String viewName = (String) requestHandler.invoke(pageController, req, res);

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
}
