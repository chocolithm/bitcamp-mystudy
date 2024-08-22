package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.context.ApplicationContext;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSession;

public class BoardAddCommand implements Command {

  private BoardDao boardDao;
  private ApplicationContext ctx;
  private SqlSession sqlSession;

  public BoardAddCommand(BoardDao boardDao, ApplicationContext ctx, SqlSession sqlSession) {
    this.boardDao = boardDao;
    this.ctx = ctx;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);

      Board board = new Board();
      board.setTitle(prompt.input("제목?"));
      board.setContent(prompt.input("내용?"));
      board.setWriter((User) ctx.getAttribute("loginUser"));

      boardDao.insert(board);
      sqlSession.commit();
    } catch (Exception e) {
      sqlSession.rollback();
      prompt.println("게시글 데이터 등록 중 오류 발생!");
      e.printStackTrace();
    }
  }

}
