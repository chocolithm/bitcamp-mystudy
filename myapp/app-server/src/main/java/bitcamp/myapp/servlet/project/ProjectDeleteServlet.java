package bitcamp.myapp.servlet.project;

import bitcamp.myapp.service.ProjectService;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/project/delete")
public class ProjectDeleteServlet extends HttpServlet {

  private ProjectService projectService;

  @Override
  public void init() throws ServletException {
    projectService = (ProjectService) this.getServletContext().getAttribute("projectService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      int projectNo = Integer.parseInt(req.getParameter("no"));

      projectService.delete(projectNo);
      res.sendRedirect("/project/list");

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
