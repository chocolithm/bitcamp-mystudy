package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/project/form")
public class ProjectFormServlet extends GenericServlet {

  private UserDao userDao;

  @Override
  public void init() throws ServletException {
    userDao = (UserDao) this.getServletContext().getAttribute("userDao");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    req.getRequestDispatcher("/header").include(req, res);

    try {

      out.println("<h1>프로젝트 등록</h1>");

      out.println("<form action='/project/add'>");
      out.println("     프로젝트명: <input name='title' type='text'><br>");
      out.println("     설명: <textarea name='description'></textarea><br>");
      out.println("     기간: <input name='startDate'' type='date'> ~");
      out.println("     <input name='endDate' type='date'><br>");
      out.println("     팀원: <br>");

      out.println("     <ul>");
      List<User> users = userDao.list();
      for (User user : users) {
        out.printf("      <li><input name='member' value='%d' type='checkbox'> %s</li>\n",
            user.getNo(), user.getName());
      }
      out.println("     </ul>");

      out.println("     <input type='submit' value='등록'>");
      out.println("</form>");

    } catch (Exception e) {
      out.println("<p>프로젝트 조회 중 오류 발생!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}
