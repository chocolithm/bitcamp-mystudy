package bitcamp.myapp.servlet.auth;

import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

  private UserService userService;

  @Override
  public void init() throws ServletException {
    userService = (UserService) this.getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setAttribute("viewName", "/auth/form.jsp");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      String email = req.getParameter("email");
      String password = req.getParameter("password");

      User user = userService.exists(email, password);
      if (user == null) {
        req.setAttribute("refresh", "2; url=login");
        req.setAttribute("viewName", "/auth/fail.jsp");
        return;
      }

      if (req.getParameter("saveEmail") != null) {
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        req.setAttribute("email", cookie);
      } else {
        Cookie cookie = new Cookie("email", "test@test.com");
        cookie.setMaxAge(0);
        req.setAttribute("email", cookie);
      }

      HttpSession session = req.getSession();
      session.setAttribute("loginUser", user);
      req.setAttribute("viewName", "redirect:/");

    } catch (Exception e) {
      req.setAttribute("exception", e);
    }
  }
}
