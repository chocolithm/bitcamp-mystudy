package bitcamp.myapp.command;

import bitcamp.myapp.util.LinkedList;
import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.Board;
import java.util.Date;

public class BoardCommand {

  LinkedList boardList = new LinkedList();

  public void executeBoardCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        this.addBoard();
        break;
      case "조회":
        this.viewBoard();
        break;
      case "목록":
        this.listBoard();
        break;
      case "변경":
        this.updateBoard();
        break;
      case "삭제":
        this.deleteBoard();
        break;
    }
  }

  private void deleteBoard() {
    int boardNo = Prompt.inputInt("게시글 번호?");
    Board deletedBoard = (Board) boardList.get(boardList.indexOf(new Board(boardNo)));
    if (deletedBoard != null) {
      boardList.remove(boardList.indexOf(deletedBoard));
      System.out.printf("%d번 게시글을 삭제 했습니다.\n", deletedBoard.getNo());
    } else {
      System.out.println("없는 게시글입니다.");
    }
  }

  private void updateBoard() {
    int boardNo = Prompt.inputInt("게시글 번호?");
    Board board = (Board) boardList.get(boardList.indexOf(new Board(boardNo)));
    if (board == null) {
      System.out.println("없는 게시글입니다.");
      return;
    }

    board.setViewCount(board.getViewCount() + 1);
    board.setTitle(Prompt.input("제목(%s)?", board.getTitle()));
    board.setContent(Prompt.input("내용(%s)?", board.getContent()));
    System.out.println("변경 했습니다.");
  }

  private void viewBoard() {
    int boardNo = Prompt.inputInt("게시글 번호?");
    Board board = (Board) boardList.get(boardList.indexOf(new Board(boardNo)));
    if (board == null) {
      System.out.println("없는 게시글입니다.");
      return;
    }

    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }

  private void listBoard() {
    System.out.println("번호 제목 작성일 조회수");
    for (Object obj : boardList.toArray()) {
      Board board = (Board) obj;
      System.out.printf("%d %s %tY-%3$tm-%3$td %d\n",
          board.getNo(), board.getTitle(), board.getCreatedDate(), board.getViewCount());
    }
  }

  private void addBoard() {
    Board board = new Board();
    board.setTitle(Prompt.input("제목?"));
    board.setContent(Prompt.input("내용?"));
    board.setCreatedDate(new Date());
    board.setNo(Board.getNextSeqNo());
    boardList.add(board);
  }

}
