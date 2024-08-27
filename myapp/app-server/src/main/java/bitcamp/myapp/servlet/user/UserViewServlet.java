package bitcamp.myapp.servlet.user;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/user/view")
public class UserViewServlet extends GenericServlet {

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

    try {
      out.println("<h1>회원 조회</h1>");

      int userNo = Integer.parseInt(req.getParameter("no"));

      User user = userDao.findBy(userNo);
      if (user == null) {
        out.println("<p>없는 회원입니다.</p>");
        return;
      }

      out.printf("<p>이름: %s</p>\n", user.getName());
      out.printf("<p>이메일: %s</p>\n", user.getEmail());
      out.printf("<p>연락처: %s</p>\n", user.getTel());
    } catch (Exception e) {
      out.println("<p>회원 데이터 조회 중 오류 발생!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}
