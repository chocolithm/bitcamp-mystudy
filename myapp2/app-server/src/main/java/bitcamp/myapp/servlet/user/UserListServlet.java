package bitcamp.myapp.servlet.user;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user/list")
public class UserListServlet implements Servlet {

  private ServletConfig config;
  private UserDao userDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    // 서블릿 객체를 생성한 후 바로 호출됨(물론, 생성자가 먼저 호출된다.)
    // 서블릿이 작업할 사용할 의존 객체를 준비하는 일을 이 메서드에서 수행한다.
    this.config = config;

    ServletContext ctx = config.getServletContext();
    userDao = (UserDao) ctx.getAttribute("userDao");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    // 웹브라우저에서 이 서블릿을 실행해달라고 요청이 들어오면 이 메서드가 호출된다.
    // 누가 호출하는가? 서블릿 컨테이너가 호출한다.

    // 출력할 콘텐트의 타입을 먼저 지정한 후 출력 스트림을 얻는다.
    res.setContentType("text/html;charset=UTF-8");

    // 출력 콘텐트를 어떤 문자집합으로 인코딩 할 지 지정하지 않고 출력 스트림을 꺼내면
    // 출력 문자열(UTF-16BE)은 ISO-8859-1 문자집합에 맞춰 인코딩된다.
    // 만약 UTF-16BE 에 있는 문자가 ISO-8859-1에 정의되어 있지 않다면,
    // '?' 문자로 변환된다.
    PrintWriter out = res.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("    <meta charset='UTF-8'>");
    out.println("    <title>Title</title>");
    out.println("</head>");
    out.println("<body>");

    try {
      out.println("<h1>회원 목록</h1>");
      out.println("<table border='1'>");
      out.println("  <thead>");
      out.println("      <tr><th>번호</th><th>이름</th><th>이메일</th></tr>");
      out.println("  </thead>");
      out.println("  <tbody>");

      for (User user : userDao.list()) {
        out.printf("      <tr><td>%d</td><td><a href='/user/view?no=%1$d'>%s</a></td><td>%s</td></tr>\n",
                user.getNo(), user.getName(), user.getEmail());
      }

      out.println("  </tbody>");
      out.println("</table>");
    } catch (Exception e) {
      out.println("<p>목록 조회 중 오류 발생!</p>");
    }

    out.println("</body>");
    out.println("</html>");
  }

  @Override
  public void destroy() {
    // 서블릿 컨테이너가 종료되기 전에 해제할 자원이 있다면 이 메서드에서 수행한다.
  }

  @Override
  public String getServletInfo() {
    // 서블릿 컨테이너 관리 화면에서 서블릿을 정보를 출력할 때 이 메서드가 호출된다.
    // 서블릿에 대한 간단한 정보를 문자열로 리턴하면 된다.
    return "회원 목록 조회";
  }

  @Override
  public ServletConfig getServletConfig() {
    // 서블릿의 정보를 조회할 때 사용할 ServletConfig 객체를 리턴해 준다.
    // 이 메서드가 리턴할 ServletConfig 객체는
    // init() 메서드가 호출될 때 파라미터로 넘어온 객체다.
    // 따라서 init() 메서드가 호출될 때 ServletConfig 객체를 보관해 둬야 한다.
    return this.config;
  }
}
