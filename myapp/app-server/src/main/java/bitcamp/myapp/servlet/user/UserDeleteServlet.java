package bitcamp.myapp.servlet.user;

import bitcamp.myapp.dao.UserDao;
import java.io.IOException;
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
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    try {
      int userNo = Integer.parseInt(req.getParameter("no"));
      if (userDao.delete(userNo)) {
        sqlSessionFactory.openSession(false).commit();
        ((HttpServletResponse) res).sendRedirect("/user/list");
      } else {
        throw new Exception("없는 회원입니다.");
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
