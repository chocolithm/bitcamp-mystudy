package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/project/form3")
public class ProjectForm3Servlet extends HttpServlet {

  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    userDao = (UserDao) this.getServletContext().getAttribute("userDao");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      req.setCharacterEncoding("UTF-8");

      Project project = (Project) req.getSession().getAttribute("project");

      String[] memberNos = req.getParameterValues("member");
      if (memberNos != null) {
        List<User> members = new ArrayList<>();
        for (String memberNo : memberNos) {
          User user = userDao.findBy(Integer.parseInt(memberNo));
          members.add(user);
        }
        project.setMembers(members);
      }

      res.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/project/form3.jsp").include(req, res);

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}