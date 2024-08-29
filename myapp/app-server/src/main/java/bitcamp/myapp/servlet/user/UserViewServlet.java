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

    req.getRequestDispatcher("/header").include(req, res);

    try {

      out.println("<h1>회원 조회</h1>");

      int userNo = Integer.parseInt(req.getParameter("no"));

      User user = userDao.findBy(userNo);
      if (user == null) {
        out.println("<p>없는 회원입니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }

      out.println("<form action='/user/update'>");
      out.printf("    번호: <input name='no' readonly type='text' value='%d'><br>\n", user.getNo());
      out.printf("    이름: <input name='name' type='text' value='%s'><br>\n", user.getName());
      out.printf("    이메일: <input name='email' type='email' value='%s'><br>\n", user.getEmail());
      out.printf("    암호: <input name='password' type='password'><br>\n");
      out.printf("    연락처: <input name='tel' type='tel' value='%s'><br>\n", user.getTel());
      out.println("   <button>변경</button>");
      out.printf(
          "   <button type='button' onclick='location.href=\"/user/delete?no=%d\"'>삭제</button>\n",
          user.getNo());
      out.println("</form>");

    } catch (Exception e) {
      out.println("<p>회원 데이터 조회 중 오류 발생!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}
