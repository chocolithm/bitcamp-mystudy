package bitcamp.myapp.servlet;

import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/header")
public class HeaderServlet extends GenericServlet {

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("<!doctype html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <meta charset='UTF-8'>");
    out.println("   <title>Document</title>");
    out.println("   <link href='/css/common.css' rel='stylesheet'>");
    out.println("</head>");
    out.println("<body>");

    out.println("<header>");
    out.println("  <a href='/'><img src='/images/home.png'></a>");
    out.println("  <h1>프로젝트 관리 시스템</h1>");

    out.println("  <nav>");
    out.println("    <ul>");
    out.println("      <li class='btn btn-default'><a href='/user/list'>회원</a></li>");
    out.println("      <li class='btn btn-default'><a href='/project/list'>프로젝트</a></li>");
    out.println("      <li class='btn btn-default'><a href='/board/list'>게시글</a></li>");
    out.println("    </ul>");
    out.println("  </nav>");

    User loginUser = (User) ((HttpServletRequest) req).getSession().getAttribute("loginUser");

    if (loginUser == null) {
      out.println("  <a class='btn btn-primary' href='/auth/form'>로그인</a>");
    } else {
      out.printf("   <a class='btn btn-light' href='/user/view?no=%d'>%s</a>\n", loginUser.getNo(),
          loginUser.getName());
      out.println("  <a class='btn btn-secondary' href='/auth/logout'>로그아웃</a>");
    }

    out.println("   </div>");
    out.println("</header>");
  }
}
