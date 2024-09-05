package bitcamp.myapp.servlet.auth;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    userDao = (UserDao) this.getServletContext().getAttribute("userDao");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    req.getRequestDispatcher("/auth/form.jsp").include(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      String email = req.getParameter("email");
      String password = req.getParameter("password");

      User user = userDao.findByEmailAndPassword(email, password);
      if (user == null) {
        res.setHeader("Refresh", "1;url=/auth/login");
        res.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/auth/fail.jsp").include(req, res);
        return;
      }

      HttpServletRequest httpReq = req;
      HttpSession session = httpReq.getSession();
      session.setAttribute("loginUser", user);
      res.sendRedirect("/");

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
