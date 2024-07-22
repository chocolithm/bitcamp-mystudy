package book.thisisjava.ch15.q07;

import java.util.ArrayList;
import java.util.List;

public class BoardDao {
  List<Board> boardList;

  public BoardDao() {
    boardList = new ArrayList<Board>();
    boardList.add(new Board("제목1", "내용1"));
    boardList.add(new Board("제목2", "내용2"));
    boardList.add(new Board("제목3", "내용3"));
  }

  public List<Board> getBoardList() {
    return boardList;
  }

}
