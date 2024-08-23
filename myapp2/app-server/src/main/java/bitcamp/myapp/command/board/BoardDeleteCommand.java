package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSessionFactory;

public class BoardDeleteCommand implements Command {

  private BoardDao boardDao;
  private SqlSessionFactory sqlSessionFactory;

  public BoardDeleteCommand(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {

    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
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
        prompt.println("변경 권한이 없습니다.");
        return;
      }

      boardDao.delete(boardNo);
      sqlSessionFactory.openSession(false).commit();
      prompt.printf("'%s'번 게시글을 삭제 했습니다.\n", deletedBoard.getNo());

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      prompt.println("삭제 중 오류 발생!");
    }
  }


}
