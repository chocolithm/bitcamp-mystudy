package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/project/view")
public class ProjectViewServlet extends GenericServlet {

  private ProjectDao projectDao;
  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    projectDao = (ProjectDao) this.getServletContext().getAttribute("projectDao");
    userDao = (UserDao) this.getServletContext().getAttribute("userDao");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    req.getRequestDispatcher("/header").include(req, res);

    try {

      out.printf("<h1>프로젝트 조회</h1>");

      int projectNo = Integer.parseInt(req.getParameter("no"));
      Project project = projectDao.findBy(projectNo);
      if (project == null) {
        out.println("<p>없는 프로젝트입니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }

      out.println("<form action='/project/update'>");
      out.printf("     번호: <input name='no' readonly type='text' value='%d'><br>\n", project.getNo());
      out.printf("     프로젝트명: <input name='title' type='text' value='%s'><br>\n", project.getTitle());
      out.printf("     설명: <textarea name='description'>%s</textarea><br>\n", project.getDescription());
      out.printf("     기간: <input name='startDate'' type='date' value='%s'> ~",
          project.getStartDate());
      out.printf("     <input name='endDate' type='date' value='%s'><br>\n", project.getEndDate());
      out.println("     팀원: <br>");

      out.println("     <ul>");
      List<User> users = userDao.list();
      for (User user : users) {
        out.printf("      <li><input %s name='member' value='%d' type='checkbox'> %s</li>\n",
            isMember(project.getMembers(), user) ? "checked" : "",
            user.getNo(), user.getName());
      }
      out.println("     </ul>");

      out.println("     <button>변경</button>");
      out.printf("     <button type='button' onclick='location.href=\"/project/delete?no=%d\"'>삭제</button>\n",
          project.getNo());
      out.println("</form>");
    } catch (Exception e) {
      out.println("<p>프로젝트 조회 중 오류 발생!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }

  private boolean isMember(List<User> members, User user) {
    for (User member : members) {
      if (member.getNo() == user.getNo()) {
        return true;
      }
    }
    return false;
  }
}
