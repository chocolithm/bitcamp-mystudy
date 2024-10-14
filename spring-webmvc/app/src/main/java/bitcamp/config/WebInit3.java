package bitcamp.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import bitcamp.AppConfig;

public class WebInit3 /*extends AbstractDispatcherServletInitializer*/ {

  //@Override
  protected WebApplicationContext createRootApplicationContext() {
    // ContextLoaderListener가 사용할 IoC Container를 리턴한다.
    // 만약 IoC Container를 리턴하지 않으면
    // ContextLoaderListener는 만들어지지 않는다.
    return null;
  }

  //@Override
  protected WebApplicationContext createServletApplicationContext() {
    System.out.println("WebInit3.createServletApplicationContext() 호출됨!");

    // DispatcherServlet이 사용할 IoC Container 리턴
    AnnotationConfigWebApplicationContext iocContainer = new AnnotationConfigWebApplicationContext();
    // iocContainer.setServletContext(servletContext);
    iocContainer.register(AppConfig.class);

    return iocContainer;
  }

  //@Override
  protected String[] getServletMappings() {
    return new String[] {"/app/*"};
  }

}
