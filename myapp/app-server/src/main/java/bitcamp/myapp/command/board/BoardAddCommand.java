package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSessionFactory;

public class BoardAddCommand implements Command {

  private BoardDao boardDao;
  private SqlSessionFactory sqlSessionFactory;

  public BoardAddCommand(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);

      Board board = new Board();
      board.setTitle(prompt.input("제목?"));
      board.setContent(prompt.input("내용?"));
      board.setWriter((User) prompt.getAttribute("loginUser"));

      boardDao.insert(board);
      sqlSessionFactory.openSession(false).commit();
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      prompt.println("게시글 데이터 등록 중 오류 발생!");
      e.printStackTrace();
    }
  }

}
