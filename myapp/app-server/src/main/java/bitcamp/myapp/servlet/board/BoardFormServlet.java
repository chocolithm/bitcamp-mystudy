package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/board/form")
public class BoardFormServlet extends GenericServlet {

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

      out.println("<h1>게시글 등록</h1>");
      
      out.println("<form action='/board/add'>");
      out.println("      제목: <input name='title' type='text'><br>");
      out.println("      내용: <textarea name='content'></textarea><br>");
      out.println("    <input type='submit' value='등록'>");
      out.println("</form>");

    } catch (Exception e) {
      out.println("<p>프로젝트 조회 중 오류 발생!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}
