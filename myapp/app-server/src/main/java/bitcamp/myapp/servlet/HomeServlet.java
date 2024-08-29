package bitcamp.myapp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/index.html")
public class HomeServlet extends GenericServlet {

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    req.getRequestDispatcher("/header").include(req, res);

    out.println("<h1>환영합니다!</h1>");
    out.println("<p>이 사이트는 팀 프로젝트를 관리하는 서비스를 제공합니다.</p>");

    out.println("</body>");
    out.println("</html>");
  }
}
