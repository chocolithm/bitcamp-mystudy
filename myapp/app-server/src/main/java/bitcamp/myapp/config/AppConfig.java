package bitcamp.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan("bitcamp.myapp.controller")
@EnableWebMvc // WebMVC 관련기능 처리코드를 활성화
public class AppConfig {
  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver vr = new InternalResourceViewResolver();
    vr.setPrefix("/WEB-INF/jsp/");
    vr.setSuffix(".jsp");
    return vr;
  }

  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }
}
