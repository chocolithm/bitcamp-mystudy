package bitcamp.myapp.servlet.board;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.vo.Board;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {

  private BoardService boardService;

  @Override
  public void init() throws ServletException {
    boardService = (BoardService) this.getServletContext().getAttribute("boardService");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      int boardNo = Integer.parseInt(req.getParameter("no"));
      Board board = boardService.get(boardNo);
      if (board == null) {
        throw new Exception("게시글이 존재하지 않습니다.");
      }

      boardService.increaseViewCount(board.getNo());
      req.setAttribute("board", board);

      res.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/board/view.jsp").include(req, res);

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
