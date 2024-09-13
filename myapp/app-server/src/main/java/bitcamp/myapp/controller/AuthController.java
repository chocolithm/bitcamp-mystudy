package bitcamp.myapp.controller;

import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthController {

  private UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping("/auth/login")
  public String login(HttpServletRequest req, HttpServletResponse res) throws Exception {
    if (req.getMethod().equals("GET")) {
      return "/auth/form.jsp";

    } else {
      String email = req.getParameter("email");
      String password = req.getParameter("password");

      User user = userService.exists(email, password);
      if (user == null) {
        res.setHeader("Refresh", "2; url=login");
        return "/auth/fail.jsp";
      }

      if (req.getParameter("saveEmail") != null) {
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        res.addCookie(cookie);
      } else {
        Cookie cookie = new Cookie("email", "test@test.com");
        cookie.setMaxAge(0);
        res.addCookie(cookie);
      }

      HttpSession session = req.getSession();
      session.setAttribute("loginUser", user);
      return "redirect:/";
    }
  }

  @RequestMapping("/auth/logout")
  public String logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
    req.getSession().invalidate();
    return "redirect:/";
  }
}
