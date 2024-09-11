package bitcamp.myapp.servlet.project;

import bitcamp.myapp.service.ProjectService;
import bitcamp.myapp.vo.Project;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/project/list")
public class ProjectListServlet extends HttpServlet {

  private ProjectService projectService;

  @Override
  public void init() throws ServletException {
    projectService = (ProjectService) this.getServletContext().getAttribute("projectService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      List<Project> list = projectService.list();
      req.setAttribute("list", list);

      // 콘텐트 타입은 include() 호출 전에 실행
      res.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/project/list.jsp").include(req, res);

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
