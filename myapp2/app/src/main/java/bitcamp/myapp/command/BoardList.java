package bitcamp.myapp.command;

import bitcamp.myapp.vo.Board;

public class BoardList extends ArrayList {

  public Board findByNo(int userNo) {
    for (int i = 0; i < size(); i++) {
      Board board = (Board) get(i);
      if (board.getNo() == userNo) {
        return board;
      }
    }
    return null;
  }

}
