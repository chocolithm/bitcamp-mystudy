package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {

  private BoardDao boardDao;
  private SqlSessionFactory sqlSessionFactory;

  @Override
  public void init() throws ServletException {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    sqlSessionFactory =
        (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      User loginUser = (User) req.getSession().getAttribute("loginUser");

      int boardNo = Integer.parseInt(req.getParameter("no"));
      Board board = boardDao.findBy(boardNo);

      if (board == null) {
        throw new Exception("없는 게시글입니다.");
      } else if (loginUser == null || (loginUser.getNo() > 10 && !loginUser.equals(board.getWriter()))) {
        throw new Exception("삭제 권한이 없습니다.");
      }

      boardDao.delete(boardNo);
      sqlSessionFactory.openSession(false).commit();
      res.sendRedirect("/board/list");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
