package bitcamp.myapp.servlet.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/auth/form")
public class LoginFormServlet extends GenericServlet {

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    req.getRequestDispatcher("/header").include(req, res);

    out.println("<h1>로그인</h1>");
    out.println("<form action='/auth/login'>");
    out.println("    이메일: <input name='email' type='email'><br>");
    out.println("    암호: <input name='password' type='password'><br>");
    out.println("    <input type='submit' value='로그인'>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}
