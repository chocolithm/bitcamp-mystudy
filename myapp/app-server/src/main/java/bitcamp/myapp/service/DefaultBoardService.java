package bitcamp.myapp.service;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DefaultBoardService implements BoardService {

  private final BoardDao boardDao;

  @Transactional // 에러가 뜨면 롤백
  public void add(Board board) throws Exception {
    boardDao.insert(board);
    if (board.getAttachedFiles().size() > 0) {
      boardDao.insertFiles(board);
    }
  }

  public List<Board> list(int pageNo, int pageSize) throws Exception {


    Map<String, Object> options = new HashMap<>();
    options.put("rowNo", (pageNo - 1) * pageSize);
    options.put("length", pageSize);

    return boardDao.list(options);
  }

  public Board get(int boardNo) throws Exception {
    return boardDao.findBy(boardNo);
  }

  @Transactional
  public void increaseViewCount(int boardNo) throws Exception {
    Board board = boardDao.findBy(boardNo);
    if (board != null) {
      boardDao.updateViewCount(board.getNo(), board.getViewCount() + 1);
    }
  }

  public int countAll() throws Exception {
    return boardDao.countAll();
  }

  @Transactional
  public boolean update(Board board) throws Exception {
    if (boardDao.update(board)) {
      if (board.getAttachedFiles().size() > 0) {
        boardDao.insertFiles(board);
      }
      return true;
    } else {
      return false;
    }
  }

  @Transactional
  public void delete(int boardNo) throws Exception {
    boardDao.deleteFiles(boardNo);
    boardDao.delete(boardNo);
  }

  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return boardDao.getFile(fileNo);
  }

  @Transactional
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return boardDao.deleteFile(fileNo);
  }
}
