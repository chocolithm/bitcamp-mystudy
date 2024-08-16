package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {

  private Connection con;

  public BoardDaoImpl(Connection con) {
    this.con = con;
  }


  @Override
  public boolean insert(Board board) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement("insert into myapp_boards(title, content, user_id) values(?, ?, ?)")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getWriter().getNo());

      stmt.executeUpdate();

      return true;
    }
  }

  @Override
  public List<Board> list() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select b.board_id, b.title, b.created_date, b.view_count, u.user_id, u.name" +
        " from myapp_boards b inner join myapp_users u on b.user_id = u.user_id" +
        " order by b.board_id asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Board> list = new ArrayList<>();

      while (rs.next()) {
        Board board = new Board();
        board.setNo(rs.getInt("board_id"));
        board.setTitle(rs.getString("title"));
        board.setCreatedDate(rs.getDate("created_date"));
        board.setViewCount(rs.getInt("view_count"));

        User user = new User();
        user.setNo(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        board.setWriter(user);

        list.add(board);
      }

      return list;
    }
  }

  @Override
  public Board findBy(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select b.board_id, b.title, b.content, b.created_date, b.view_count, u.user_id, u.name" +
        " from myapp_boards b inner join myapp_users u on b.user_id = u.user_id" +
        " where b.board_id = ?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Board board = new Board();
          board.setNo(rs.getInt("board_id"));
          board.setTitle(rs.getString("title"));
          board.setContent(rs.getString("content"));
          board.setCreatedDate(rs.getTimestamp("created_date"));
          board.setViewCount(rs.getInt("view_count"));

          User user = new User();
          user.setNo(rs.getInt("user_id"));
          user.setName(rs.getString("name"));
          board.setWriter(user);

          return board;
        }
      }

      return null;
    }
  }

  @Override
  public boolean update(Board board) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update myapp_boards set title = ?, content = ? where board_id = ?")) {

      stmt.setString(1, board.getTitle());
      stmt.setString(2, board.getContent());
      stmt.setInt(3, board.getNo());

      return stmt.executeUpdate() > 0;
    }
  }

  @Override
  public boolean delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement("delete from myapp_boards where board_id = ?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate() > 0;
    }
  }

  @Override
  public void updateViewCount(int boardNo, int count) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update myapp_boards set view_count = ? where board_id = ?")) {

      stmt.setInt(1, count);
      stmt.setInt(2, boardNo);

      stmt.executeUpdate();
    }
  }
}
