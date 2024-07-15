package bitcamp.myapp.command.board;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.Date;
import java.util.List;

public class BoardAddCommand implements Command {

  private List<Board> boardList;

  public BoardAddCommand(List<Board> list) {
    this.boardList = list;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);
    
    Board board = new Board();
    board.setTitle(Prompt.input("제목?"));
    board.setContent(Prompt.input("내용?"));
    board.setCreatedDate(new Date());
    board.setNo(Board.getNextSeqNo());
    boardList.add(board);
  }

}
