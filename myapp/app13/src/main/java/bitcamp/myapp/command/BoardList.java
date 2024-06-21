package bitcamp.myapp.command;

import bitcamp.myapp.vo.Board;

public class BoardList {

  private static final int MAX_SIZE = 100;

  private Board[] boards = new Board[MAX_SIZE];
  private int boardLength = 0;

  public void add(Board board) {
    this.boards[this.boardLength++] = board;
  }

  public Board delete(int boardNo) {
    Board deletedBoard = findByNo(boardNo);
    if (deletedBoard == null) {
      return null;
    }

    int index = indexOf(deletedBoard);
    for (int i = index + 1; i < this.boardLength; i++) {
      this.boards[i - 1] = this.boards[i];
    }
    this.boards[--this.boardLength] = null;
    return deletedBoard;
  }

  public Board[] toArray() {
    Board[] arr = new Board[this.boardLength];
    for (int i = 0; i < this.boardLength; i++) {
      arr[i] = this.boards[i];
    }
    return arr;
  }

  public Board findByNo(int boardNo) {
    for (int i = 0; i < this.boardLength; i++) {
      if (this.boards[i].getNo() == boardNo) {
        return this.boards[i];
      }
    }
    return null;
  }

  public int indexOf(Board board) {
    for (int i = 0; i < this.boardLength; i++) {
      if (board == this.boards[i]) {
        return i;
      }
    }
    return -1;
  }
}
