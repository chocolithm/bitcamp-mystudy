package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/update")
public class BoardUpdateServlet extends GenericServlet {

  private BoardDao boardDao;
  private SqlSessionFactory sqlSessionFactory;

  @Override
  public void init() throws ServletException {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    sqlSessionFactory =
        (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();

    req.getRequestDispatcher("/header").include(req, res);
    ((HttpServletResponse) res).setHeader("Refresh", "1;url=/board/list");

    try {

      out.println("<p>게시글 변경 결과</p>");

      User loginUser = (User) ((HttpServletRequest) req).getSession().getAttribute("loginUser");

      int boardNo = Integer.parseInt(req.getParameter("no"));
      Board board = boardDao.findBy(boardNo);

      if (board == null) {
        out.println("<p>없는 게시글입니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      } else if (loginUser == null || (loginUser.getNo() > 10 && !loginUser.equals(
          board.getWriter()))) {
        out.println("<p>변경 권한이 없습니다.</p>");
        out.println("</body>");
        out.println("</html>");
        return;
      }

      board.setTitle(req.getParameter("title"));
      board.setContent(req.getParameter("content"));
      boardDao.update(board);
      sqlSessionFactory.openSession(false).commit();
      out.println("<p>변경 했습니다.</p>");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("게시글 데이터 변경 중 오류 발생!");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}
