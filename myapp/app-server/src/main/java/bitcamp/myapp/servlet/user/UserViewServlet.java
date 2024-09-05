package bitcamp.myapp.servlet.user;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/view")
public class UserViewServlet extends HttpServlet {

  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    userDao = (UserDao) this.getServletContext().getAttribute("userDao");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      int userNo = Integer.parseInt(req.getParameter("no"));
      User user = userDao.findBy(userNo);
      req.setAttribute("user", user);

      res.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/user/view.jsp").include(req, res);

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
