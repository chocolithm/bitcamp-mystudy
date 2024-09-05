package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/project/view")
public class ProjectViewServlet extends HttpServlet {

  private ProjectDao projectDao;
  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    projectDao = (ProjectDao) this.getServletContext().getAttribute("projectDao");
    userDao = (UserDao) this.getServletContext().getAttribute("userDao");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      int projectNo = Integer.parseInt(req.getParameter("no"));
      Project project = projectDao.findBy(projectNo);
      List<User> users = userDao.list();

      req.setAttribute("project", project);
      req.setAttribute("users", users);

      res.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/project/view.jsp").include(req, res);

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
