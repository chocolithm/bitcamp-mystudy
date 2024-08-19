package bitcamp.myapp.dao.mysql;

import bitcamp.bitbatis.SqlSession;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import java.util.List;

public class BoardDaoImpl implements BoardDao {

  private SqlSession sqlSession;

  public BoardDaoImpl(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public boolean insert(Board board) throws Exception {
    int count = sqlSession.insert(
        "insert into myapp_boards(title, content, user_id) values (?, ?, ?)",
        board.getTitle(),
        board.getContent(),
        board.getWriter().getNo());
    return count > 0;
  }

  @Override
  public List<Board> list() throws Exception {
    return sqlSession.selectList(
        "select "
            + " b.board_id as no,"
            + " b.title,"
            + " b.created_date as createdDate,"
            + " b.view_count as viewCount,"
            + " u.user_id as writer_no,"
            + " u.name as writer_name"
            + " from myapp_boards b inner join myapp_users u on b.user_id=u.user_id"
            + " order by b.board_id asc",
        Board.class);
  }

  @Override
  public Board findBy(int no) throws Exception {
    return sqlSession.selectOne(
        "select "
            + " b.board_id as no,"
            + " b.title,"
            + " b.content,"
            + " b.created_date as createdDate,"
            + " b.view_count as viewCount,"
            + " u.user_id as writer_no,"
            + " u.name as writer_name"
            + " from myapp_boards b inner join myapp_users u on b.user_id=u.user_id"
            + " where b.board_id=?",
        Board.class,
        no);
  }

  @Override
  public boolean update(Board board) throws Exception {
    int count = sqlSession.update(
        "update myapp_boards set"
            + " title=?,"
            + " content=?"
            + " where board_id=?",
        board.getTitle(),
        board.getContent(),
        board.getNo());
    return count > 0;
  }

  @Override
  public boolean delete(int no) throws Exception {
    int count = sqlSession.delete("delete from myapp_boards where board_id=?", no);
    return count > 0;
  }

  @Override
  public void updateViewCount(int boardNo, int count) throws Exception {
    sqlSession.update("update myapp_boards set"
            + " view_count=?"
            + " where board_id=?",
        count,
        boardNo);
  }
}
