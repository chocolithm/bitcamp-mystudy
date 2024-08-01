package bitcamp.myapp.command.board;

import bitcamp.command.Command;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.Date;

public class BoardAddCommand implements Command {

  private BoardDao boardDao;

  public BoardAddCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);
    try {
      Board board = new Board();
      board.setTitle(Prompt.input("제목?"));
      board.setContent(Prompt.input("내용?"));
      board.setCreatedDate(new Date());

      boardDao.insert(board);
    } catch (Exception e) {
      System.out.println("등록 중 오류 발생!");
    }
  }

}
