package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/project/list")
public class ProjectListServlet extends GenericServlet {

  private ProjectDao projectDao;

  @Override
  public void init() throws ServletException {
    projectDao = (ProjectDao) this.getServletContext().getAttribute("projectDao");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    try {
      List<Project> list = projectDao.list();
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
