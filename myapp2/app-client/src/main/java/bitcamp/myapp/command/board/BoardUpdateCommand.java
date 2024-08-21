package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.context.ApplicationContext;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import org.apache.ibatis.session.SqlSession;

public class BoardUpdateCommand implements Command {

  private BoardDao boardDao;
  private ApplicationContext ctx;
  private SqlSession sqlSession;

  public BoardUpdateCommand(BoardDao boardDao, ApplicationContext ctx, SqlSession sqlSession) {
    this.boardDao = boardDao;
    this.ctx = ctx;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(String menuName) {
    User loginUser = (User) ctx.getAttribute("loginUser");

    System.out.printf("[%s]\n", menuName);
    int boardNo = Prompt.inputInt("게시글 번호?");

    try {
      Board board = boardDao.findBy(boardNo);
      if (board == null) {
        System.out.println("없는 게시글입니다.");
        return;
      } else if (loginUser.getNo() > 10 && board.getWriter().getNo() != loginUser.getNo()) {
        System.out.println("변경 권한이 없습니다.");
        return;
      }

      board.setTitle(Prompt.input("제목(%s)?", board.getTitle()));
      board.setContent(Prompt.input("내용(%s)?", board.getContent()));

      boardDao.update(board);
      sqlSession.commit();
      System.out.println("변경 했습니다.");

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println("변경 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
