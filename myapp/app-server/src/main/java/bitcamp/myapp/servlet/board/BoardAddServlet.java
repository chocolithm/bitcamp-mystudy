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

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {

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
    res.setContentType("text/html;charset=UTF-8");
    req.getRequestDispatcher("/board/form.jsp").include(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      req.setCharacterEncoding("UTF-8");

      Board board = new Board();
      board.setTitle(req.getParameter("title"));
      board.setContent(req.getParameter("content"));

      User loginUser = (User) ((HttpServletRequest) req).getSession().getAttribute("loginUser");
      board.setWriter(loginUser);

      boardDao.insert(board);
      sqlSessionFactory.openSession(false).commit();
      res.sendRedirect("/board/list");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
