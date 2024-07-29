package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Board;
import java.util.List;

public interface BoardDao {
  boolean insert(Board board) throws Exception;

  Board findBy(int no) throws Exception;

  List<Board> list() throws Exception;

  boolean update(Board board) throws Exception;

  boolean delete(Board board) throws Exception;
}
