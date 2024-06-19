package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.Board;

import java.util.Date;

public class BoardCommand {

  private static final int MAX_SIZE = 100;
  private static Board[] boards = new Board[MAX_SIZE];
  private static int boardLength = 0;

  public static void executeBoardCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        addBoard();
        break;
      case "조회":
        viewBoard();
        break;
      case "목록":
        listBoard();
        break;
      case "변경":
        updateBoard();
        break;
      case "삭제":
        deleteBoard();
        break;
    }
  }

  private static void addBoard() {
    Board board = new Board();
    board.setTitle(Prompt.input("제목?"));
    board.setContent(Prompt.input("내용?"));
    board.setCreatedDate(new Date());
    boards[boardLength++] = board;
    System.out.println("등록했습니다.");
  }

  private static void listBoard() {
    System.out.println("번호 제목 작성일 조회수");
    for (int i = 0; i < boardLength; i++) {
      Board board = boards[i];
      System.out.printf("%d %s %tY-%3$tm-%3$td %d\n",
          (i + 1), board.getTitle(), board.getCreatedDate(), board.getViewCount());
    }
  }

  private static void viewBoard() {
    int boardNo = Prompt.inputInt("게시글 번호?");
    if (boardNo < 1 || boardNo > boardLength) {
      System.out.println("없는 게시글입니다.");
      return;
    }
    Board board = boards[boardNo - 1];
    board.setViewCount(board.getViewCount() + 1);
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성일: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS\n", board.getCreatedDate());
    System.out.printf("조회수: %d\n", board.getViewCount());
  }

  private static void updateBoard() {
    int boardNo = Prompt.inputInt("게시글 번호?");
    if (boardNo < 1 || boardNo > boardLength) {
      System.out.println("없는 게시글입니다.");
      return;
    }
    Board board = boards[boardNo - 1];
    board.setViewCount(board.getViewCount() + 1);
    board.setTitle(Prompt.input("제목(%s)?", board.getTitle()));
    board.setContent(Prompt.input("내용(%s)?", board.getContent()));
    System.out.println("변경했습니다.");
  }

  private static void deleteBoard() {
    int boardNo = Prompt.inputInt("게시글 번호?");
    if (boardNo < 1 || boardNo > boardLength) {
      System.out.println("없는 게시글입니다.");
      return;
    }

    for (int i = boardNo; i < boardLength; i++) {
      boards[i - 1] = boards[i];
    }
    boards[--boardLength] = null;
    System.out.println("삭제했습니다.");
  }
}
