package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.ProjectDao;
import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/project/delete")
public class ProjectDeleteServlet extends HttpServlet {

  private ProjectDao projectDao;
  private SqlSessionFactory sqlSessionFactory;

  @Override
  public void init() throws ServletException {
    projectDao = (ProjectDao) this.getServletContext().getAttribute("projectDao");
    sqlSessionFactory =
        (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      int projectNo = Integer.parseInt(req.getParameter("no"));

      projectDao.deleteMembers(projectNo);
      if (projectDao.delete(projectNo)) {
        sqlSessionFactory.openSession(false).commit();
        res.sendRedirect("/project/list");
      } else {
        throw new Exception("없는 프로젝트입니다.");
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
