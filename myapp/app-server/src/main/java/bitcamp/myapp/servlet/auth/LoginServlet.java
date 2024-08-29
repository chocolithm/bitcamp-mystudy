package bitcamp.myapp.servlet.auth;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth/login")
public class LoginServlet extends GenericServlet {

  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    userDao = (UserDao) this.getServletContext().getAttribute("userDao");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    req.getRequestDispatcher("/header").include(req, res);

    try {

      out.println("<h1>로그인 결과</h1>");

      String email = req.getParameter("email");
      String password = req.getParameter("password");

      User user = userDao.findByEmailAndPassword(email, password);
      if (user == null) {
        out.println("<p>이메일 또는 암호가 맞지 않습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        ((HttpServletResponse) res).setHeader("Refresh", "1;url=/auth/form");
        return;
      }

      // req가 실제로는 HttpServletRequest 이므로 형변환 후 getSession() 수행
      HttpServletRequest httpReq = (HttpServletRequest) req;
      HttpSession session = httpReq.getSession();
      session.setAttribute("loginUser", user);
      out.println("<p>로그인 성공입니다!</p>");

    } catch (Exception e) {
      out.println("<p>로그인 중 오류 발생!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
    ((HttpServletResponse) res).sendRedirect("/");
  }
}
