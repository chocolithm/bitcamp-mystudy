package bitcamp.myapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

@WebFilter(
    urlPatterns = "/*",
    initParams = @WebInitParam(name = "encoding", value = "UTF-8")
)
public class CharacterEncodingFilter implements Filter {

  private String encoding = "UTF-8";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    if (filterConfig.getInitParameter("encoding") != null) {
      encoding = filterConfig.getInitParameter("encoding");
    }
    System.out.println("CharacterEncodingFilter 객체 준비!");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    if (httpRequest.getMethod().equals("POST")) {
      request.setCharacterEncoding(encoding);
    }

    chain.doFilter(request, response);
  }
}
