package bitcamp.myapp.servlet.user;

import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/view")
public class UserViewServlet extends HttpServlet {

  private UserService userService;

  @Override
  public void init() throws ServletException {
    userService = (UserService) this.getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      int userNo = Integer.parseInt(req.getParameter("no"));
      User user = userService.get(userNo);
      req.setAttribute("user", user);

      res.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/user/view.jsp").include(req, res);

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
