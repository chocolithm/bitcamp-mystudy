package bitcamp.myapp.config;

import java.io.File;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebApplicationInitializerImpl extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    // ContextLoaderListener의 IoC Container 설정
    // - DB 관련 객체 준비
    // - 서비스 관련 객체 준비
    return new Class[] {RootConfig.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    // DispatcherServlet의 IoC Container 설정
    // - Web 관련 객체 준비
    return new Class[] {AppConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/app/*"};
  }

  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
    registration.setMultipartConfig(new MultipartConfigElement(
        new File("./temp/").getAbsolutePath(),
        1024 * 1024 * 20,
        1024 * 1024 * 100,
        1024 * 1024
    ));
  }

  @Override
  protected Filter[] getServletFilters() {
    return new Filter[] {new CharacterEncodingFilter("UTF-8")};
  }
}
