package bitcamp.myapp.command.board;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;
import java.util.List;

public class BoardDeleteCommand implements Command {

  private List<Board> boardList;

  public BoardDeleteCommand(List<Board> list) {
    this.boardList = list;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    int boardNo = Prompt.inputInt("게시글 번호?");
    int index = boardList.indexOf(new Board(boardNo));
    if (index == -1) {
      System.out.println("없는 게시글입니다.");
      return;
    }

    Board deletedBoard = boardList.remove(index);
    System.out.printf("%d번 게시글을 삭제 했습니다.\n", deletedBoard.getNo());
  }
}
