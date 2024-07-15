package bitcamp.myapp.command.board;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Board;
import bitcamp.util.util.Prompt;
import java.util.List;

public class BoardViewCommand implements Command {

  private List<Board> boardList;

  public BoardViewCommand(List<Board> list) {
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

    Board board = boardList.get(index);

    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }
}
