package bitcamp;

import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

@HandlesTypes(WebInit.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

  @Override
  public void onStartup(Set<Class<?>> classes, ServletContext ctx) throws ServletException {
    System.out.println("MyServletContainerInitializer:onStartup()");

    if (classes == null) {
      return;
    }

    for (Class<?> clazz : classes) {
      try {
        WebInit webInit = (WebInit) clazz.getConstructor().newInstance();
        webInit.start(ctx);
        System.out.println(clazz.getName() + " 클래스의 start() 호출됨");

      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

}
