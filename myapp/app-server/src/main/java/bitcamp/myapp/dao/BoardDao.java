package bitcamp.myapp.dao;

import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import java.util.List;

public interface BoardDao {
  boolean insert(Board board) throws Exception;

  List<Board> list() throws Exception;

  Board findBy(int no) throws Exception;

  boolean update(Board board) throws Exception;

  boolean delete(int no) throws Exception;

  void updateViewCount(@Param("no") int boardNo, @Param("count") int count) throws Exception;

  void insertFiles(Board board) throws Exception;

  AttachedFile getFile(int fileNo) throws Exception;

  boolean deleteFile(int fileNo) throws Exception;

  boolean deleteFiles(int boardNo) throws Exception;
}
