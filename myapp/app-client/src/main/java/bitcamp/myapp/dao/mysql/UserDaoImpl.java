package bitcamp.myapp.dao.mysql;

import bitcamp.bitbatis.SqlSession;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.util.List;

public class UserDaoImpl implements UserDao {

  private SqlSession sqlSession;

  public UserDaoImpl(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public boolean insert(User user) throws Exception {

    int count = sqlSession.insert(
        "insert into myapp_users(name, email, pwd, tel) values(?, ?, sha1(?), ?)",
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        user.getTel()
    );

    return count > 0;
  }

  @Override
  public List<User> list() throws Exception {

    return sqlSession.selectList(
        "select" +
            " user_id as no," +
            " name," +
            " email" +
            " from myapp_users" +
            " order by user_id asc",
        User.class
    );
  }

  @Override
  public User findBy(int no) throws Exception {
    return sqlSession.selectOne(
        "select" +
            " user_id as no," +
            " name," +
            " email," +
            " tel" +
            " from myapp_users" +
            " where user_id = ?",
        User.class,
        no
    );
  }

  @Override
  public User findByEmailAndPassword(String email, String password) throws Exception {
    return sqlSession.selectOne(
        "select" +
            " user_id as no," +
            " name," +
            " email," +
            " tel" +
            " from myapp_users" +
            " where email = ? and pwd = sha1(?)",
        User.class,
        email,
        password
    );
  }

  @Override
  public boolean update(User user) throws Exception {
    int count = sqlSession.update(
        "update myapp_users set name = ?, email = ?, pwd = sha1(?), tel = ? where user_id = ?",
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        user.getTel(),
        user.getNo()
    );

    return count > 0;
  }

  @Override
  public boolean delete(int no) throws Exception {
    int count = sqlSession.delete(
        "delete from myapp_users where user_id = ?",
        no
    );

    return count > 0;
  }
}
