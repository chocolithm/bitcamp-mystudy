package bitcamp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.context.WebApplicationContext;

public class WebInit2 /*extends AbstractContextLoaderInitializer*/ {

  //@Override
  protected WebApplicationContext createRootApplicationContext() {
    // ContextLoaderListener가 사용할 IoC Container를 리턴한다.
    // 만약 IoC Container를 리턴하지 않으면
    // ContextLoaderListener는 만들어지지 않는다.
    return null;
  }


  //@Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    System.out.println("WebInit2.onStartup() 호출됨!");

    /*
    super.onStartup(servletContext);

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
