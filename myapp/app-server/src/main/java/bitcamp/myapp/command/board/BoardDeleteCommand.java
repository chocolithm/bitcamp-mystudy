package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import bitcamp.mybatis.SqlSessionFactoryProxy;
import bitcamp.net.Prompt;

public class BoardDeleteCommand implements Command {

  private BoardDao boardDao;
  private SqlSessionFactoryProxy sqlSessionFactoryProxy;

  public BoardDeleteCommand(BoardDao boardDao, SqlSessionFactoryProxy sqlSessionFactoryProxy) {
    this.boardDao = boardDao;
    this.sqlSessionFactoryProxy = sqlSessionFactoryProxy;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      User loginUser = (User) prompt.getAttribute("loginUser");

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
      sqlSessionFactoryProxy.openSession(false).commit();
      prompt.printf("%d번 게시글을 삭제 했습니다.\n", deletedBoard.getNo());
    } catch (Exception e) {
      sqlSessionFactoryProxy.openSession(false).rollback();
      prompt.println("게시글 데이터 삭제 중 오류 발생!");
      e.printStackTrace();
    }

  }
}
