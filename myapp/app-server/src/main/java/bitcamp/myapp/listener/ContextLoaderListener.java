package bitcamp.myapp.listener;

import bitcamp.myapp.config.AppConfig;
import java.io.File;
import java.util.EnumSet;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener // 서블릿 컨테이너에 이 클래스를 배치하는 태그
public class ContextLoaderListener implements ServletContextListener {


  @Override
  public void contextInitialized(ServletContextEvent sce) {

    try {
      ServletContext ctx = sce.getServletContext();

      AnnotationConfigWebApplicationContext iocContainer = new AnnotationConfigWebApplicationContext();
      iocContainer.register(AppConfig.class);
      iocContainer.setServletContext(ctx);
      iocContainer.refresh(); // register한 후 refresh를 통해 객체 준비

      ctx.setAttribute("sqlSessionFactory", iocContainer.getBean(SqlSessionFactory.class));

      DispatcherServlet dispatcherServlet = new DispatcherServlet(iocContainer);
      ServletRegistration.Dynamic servletRegistration = ctx.addServlet("app", dispatcherServlet);
      servletRegistration.addMapping("/app/*");
      servletRegistration.setLoadOnStartup(1);
      servletRegistration.setMultipartConfig(new MultipartConfigElement(
          new File("./temp/").getAbsolutePath(),
          1024 * 1024 * 20,
          1024 * 1024 * 100,
          1024 * 1024
      ));

      CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8");
      FilterRegistration.Dynamic filterRegistration = ctx.addFilter("characterEncodingFilter", characterEncodingFilter);
      filterRegistration.addMappingForServletNames(
          EnumSet.of(DispatcherType.REQUEST, DispatcherType.INCLUDE, DispatcherType.FORWARD),
          false,
          "app"
      );

    } catch (Exception e) {
      System.out.println("서비스 객체 준비 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
