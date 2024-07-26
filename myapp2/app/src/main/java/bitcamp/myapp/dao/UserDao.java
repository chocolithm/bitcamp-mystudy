package bitcamp.myapp.dao;

import bitcamp.myapp.vo.User;
import java.util.List;

public interface UserDao {
  boolean insert(User user);

  User findBy(int no);

  List<User> list();

  boolean update(User user);

  boolean delete(User user);
}
