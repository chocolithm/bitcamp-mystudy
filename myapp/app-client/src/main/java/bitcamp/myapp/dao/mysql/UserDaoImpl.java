package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

  private Connection con;

  public UserDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public boolean insert(User user) throws Exception {
    try (Statement stmt = con.createStatement()) {
      stmt.executeUpdate(String.format(
          "insert into myapp_users(name, email, pwd, tel)" +
              " values('%s', '%s', sha1('%s'), '%s')",
          user.getName(),
          user.getEmail(),
          user.getPassword(),
          user.getTel()
      ));

      return true;
    }
  }

  @Override
  public List<User> list() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from myapp_users order by user_id asc")) {

      ArrayList<User> list = new ArrayList<>();

      while (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));

        list.add(user);
      }

      return list;
    }
  }

  @Override
  public User findBy(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from myapp_users where user_id = " + no)) {

      if (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setTel(rs.getString("tel"));

        return user;
      }

      return null;
    }
  }

  @Override
  public boolean update(User user) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int count = stmt.executeUpdate(String.format(
          "update myapp_users set" +
              " name = '%s'," +
              " email = '%s'," +
              " pwd = sha1('%s')," +
              " tel = '%s'" +
              " where user_id = %d",
          user.getName(),
          user.getEmail(),
          user.getPassword(),
          user.getTel(),
          user.getNo()
      ));

      return count > 0;
    }
  }

  @Override
  public boolean delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int count = stmt.executeUpdate(String.format(
          "delete from myapp_users where user_id = %d", no));

      return count > 0;
    }
  }
}
