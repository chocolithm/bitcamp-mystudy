package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.context.ApplicationContext;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import org.apache.ibatis.session.SqlSession;

public class BoardDeleteCommand implements Command {

  private BoardDao boardDao;
  private ApplicationContext ctx;
  private SqlSession sqlSession;

  public BoardDeleteCommand(BoardDao boardDao, ApplicationContext ctx, SqlSession sqlSession) {
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
      Board deletedBoard = boardDao.findBy(boardNo);
      if (deletedBoard == null) {
        System.out.println("없는 게시글입니다.");
        return;
      } else if (loginUser.getNo() > 10 && deletedBoard.getWriter().getNo() != loginUser.getNo()) {
        System.out.println("변경 권한이 없습니다.");
        return;
      }

      boardDao.delete(boardNo);
      sqlSession.commit();
      System.out.printf("'%s'번 게시글을 삭제 했습니다.\n", deletedBoard.getNo());

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println("삭제 중 오류 발생!");
    }
  }


}
