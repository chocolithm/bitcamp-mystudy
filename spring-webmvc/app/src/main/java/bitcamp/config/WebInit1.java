package bitcamp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import bitcamp.AppConfig;
import bitcamp.WebInit;

public class WebInit1 implements WebInit {

  @Override
  public void start(ServletContext ctx) {
    AnnotationConfigWebApplicationContext iocContainer = new AnnotationConfigWebApplicationContext();
    iocContainer.setServletContext(ctx);
    iocContainer.register(AppConfig.class);
    iocContainer.refresh();

    DispatcherServlet frontController = new DispatcherServlet(iocContainer);

    Dynamic options = ctx.addServlet("app", frontController);

    options.addMapping("/app/*");
  }

}
