package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/project/view")
public class ProjectViewServlet extends GenericServlet {

  private ProjectDao projectDao;

  @Override
  public void init() throws ServletException {
    projectDao = (ProjectDao) this.getServletContext().getAttribute("projectDao");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    out.println("<!doctype html>");
    out.println("<html>");
    out.println("<head>");
    out.println("   <meta charset='UTF-8'>");
    out.println("   <title>Document</title>");
    out.println("</head>");
    out.println("<body>");

    try {
      out.printf("<h1>프로젝트 조회</h1>");

      int projectNo = Integer.parseInt(req.getParameter("no"));
      Project project = projectDao.findBy(projectNo);
      if (project == null) {
        out.println("<p>없는 프로젝트입니다.</p>");
        return;
      }

      out.printf("<p>프로젝트명: %s</p>\n", project.getTitle());
      out.printf("<p>설명: %s</p>\n", project.getDescription());
      out.printf("<p>기간: %s ~ %s</p>\n", project.getStartDate(), project.getEndDate());
      out.println("<p>팀원:</p>");
      for (User user : project.getMembers()) {
        out.printf("<p>- %s</p>\n", user.getName());
      }
    } catch (Exception e) {
      out.println("<p>프로젝트 조회 중 오류 발생!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}
