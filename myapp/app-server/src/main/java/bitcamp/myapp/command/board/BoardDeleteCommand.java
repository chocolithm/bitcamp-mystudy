package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.context.ApplicationContext;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
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
  public void execute(String menuName, Prompt prompt) {
    try {
      User loginUser = (User) ctx.getAttribute("loginUser");
      
      prompt.printf("[%s]\n", menuName);
      int boardNo = prompt.inputInt("게시글 번호?");

      Board deletedBoard = boardDao.findBy(boardNo);
      if (deletedBoard == null) {
        prompt.println("없는 게시글입니다.");
        return;
      } else if (loginUser.getNo() > 10 && deletedBoard.getWriter().getNo() != loginUser.getNo()) {
        prompt.println("삭제 권한이 없습니다.");
        return;
      }

      boardDao.delete(boardNo);
      sqlSession.commit();
      prompt.printf("%d번 게시글을 삭제 했습니다.\n", deletedBoard.getNo());
    } catch (Exception e) {
      sqlSession.rollback();
      prompt.println("게시글 데이터 삭제 중 오류 발생!");
      e.printStackTrace();
    }

  }
}
