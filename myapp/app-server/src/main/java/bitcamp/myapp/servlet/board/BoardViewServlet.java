package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/board/view")
public class BoardViewServlet extends GenericServlet {

  private BoardDao boardDao;
  private SqlSessionFactory sqlSessionFactory;

  @Override
  public void init() throws ServletException {
    boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    sqlSessionFactory =
        (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
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
