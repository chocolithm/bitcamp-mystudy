package bitcamp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebInit1 /*implements WebApplicationInitializer*/ {

  // @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    System.out.println("WebInit1.onStartup() 호출됨!");

    /*
    AnnotationConfigWebApplicationContext iocContainer = new AnnotationConfigWebApplicationContext();
    iocContainer.setServletContext(servletContext);
    iocContainer.register(AppConfig.class);
    iocContainer.refresh();

    DispatcherServlet frontController = new DispatcherServlet(iocContainer);

    Dynamic options = servletContext.addServlet("app", frontController);

    options.addMapping("/app/*");
     */

  }

}
