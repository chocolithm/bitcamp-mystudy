package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {

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
      int boardNo = Integer.parseInt(req.getParameter("no"));
      Board board = boardDao.findBy(boardNo);

      board.setViewCount(board.getViewCount() + 1);
      boardDao.updateViewCount(board.getNo(), board.getViewCount());

      req.setAttribute("board", board);

      res.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/board/view.jsp").include(req, res);

      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      e.printStackTrace();
      sqlSessionFactory.openSession(false).rollback();
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
