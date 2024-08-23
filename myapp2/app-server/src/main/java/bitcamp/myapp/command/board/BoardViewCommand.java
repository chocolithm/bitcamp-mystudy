package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSessionFactory;

public class BoardViewCommand implements Command {

  private BoardDao boardDao;
  private SqlSessionFactory sqlSessionFactory;

  public BoardViewCommand(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {

    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);
      int boardNo = prompt.inputInt("게시글 번호?");

      Board board = boardDao.findBy(boardNo);
      if (board == null) {
        prompt.println("없는 게시글입니다.");
        return;
      }

      board.setViewCount(board.getViewCount() + 1);
      boardDao.updateViewCount(board.getNo(), board.getViewCount());
      sqlSessionFactory.openSession(false).commit();

      prompt.printf("제목: %s\n", board.getTitle());
      prompt.printf("내용: %s\n", board.getContent());
      prompt.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());
      prompt.printf("조회수: %d\n", board.getViewCount());
      prompt.printf("작성자: %s\n", board.getWriter().getName());

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      prompt.println("조회 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
