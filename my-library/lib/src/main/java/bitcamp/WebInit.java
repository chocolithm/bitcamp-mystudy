package bitcamp;

import javax.servlet.ServletContext;

// 서블릿 컨테이너가 실행될 때 초기화 작업을 담당할 객체의 사용규칙
public interface WebInit {
  public void start(ServletContext ctx);
}
