package bitcamp.myapp.dao;

import bitcamp.myapp.vo.User;
import java.util.List;

public interface UserDao {
  boolean insert(User user) throws Exception;

  User findBy(int no) throws Exception;

  List<User> list() throws Exception;

  boolean update(User user) throws Exception;

  boolean delete(User user) throws Exception;
}
