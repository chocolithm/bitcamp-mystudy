package bitcamp.myapp.servlet.user;

import bitcamp.myapp.dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/delete")
public class UserDeleteServlet extends GenericServlet {

  private UserDao userDao;
  private SqlSessionFactory sqlSessionFactory;

  @Override
  public void init() throws ServletException {
    userDao = (UserDao) this.getServletContext().getAttribute("userDao");
    sqlSessionFactory =
        (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    req.getRequestDispatcher("/header").include(req, res);
    ((HttpServletResponse) res).setHeader("Refresh", "1;url=/user/list");

    try {
      out.println("<h1>회원 삭제 결과</h1>");

      int userNo = Integer.parseInt(req.getParameter("no"));

      if (userDao.delete(userNo)) {
        sqlSessionFactory.openSession(false).commit();
        out.println("<p>삭제 했습니다.</p>");
      } else {
        out.println("<p>없는 회원입니다.</p>");
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>회원 데이터 삭제 중 오류 발생!</p>");
    }
  }
}
