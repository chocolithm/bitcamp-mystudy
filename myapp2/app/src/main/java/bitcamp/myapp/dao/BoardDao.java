package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import java.util.List;

public interface BoardDao {
  boolean insert(Board board);

  Board findBy(int no);

  List<Board> list();

  boolean update(Board board);

  boolean delete(Board board);
}
