package bitcamp.myapp.service;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import java.util.List;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultBoardService implements BoardService {

  private BoardDao boardDao;
  private SqlSessionFactory sqlSessionFactory;

  public DefaultBoardService(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void add(Board board) throws Exception {
    try {
      boardDao.insert(board);
      if (board.getAttachedFiles().size() > 0) {
        boardDao.insertFiles(board);
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw e;
    }
  }

  @Override
  public List<Board> list() throws Exception {
    return boardDao.list();
  }

  @Override
  public Board get(int boardNo) throws Exception {
    return boardDao.findBy(boardNo);
  }

  @Override
  public void increaseViewCount(int boardNo) throws Exception {
    try {
      Board board = boardDao.findBy(boardNo);
      if (board != null) {
        boardDao.updateViewCount(board.getNo(), board.getViewCount() + 1);
        sqlSessionFactory.openSession(false).commit();
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw e;
    }
  }

  @Override
  public boolean update(Board board) throws Exception {
    try {
      if (boardDao.update(board)) {
        if (board.getAttachedFiles().size() > 0) {
          boardDao.insertFiles(board);
        }
        sqlSessionFactory.openSession(false).commit();
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw e;
    }
  }

  @Override
  public void delete(int boardNo) throws Exception {
    try {
      boardDao.deleteFiles(boardNo);
      boardDao.delete(boardNo);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
    }
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return boardDao.getFile(fileNo);
  }

  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    try {
      if (boardDao.deleteFile(fileNo)) {
        sqlSessionFactory.openSession(false).commit();
        return true;
      } else {
        return false;
      }
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw e;
    }
  }
}
