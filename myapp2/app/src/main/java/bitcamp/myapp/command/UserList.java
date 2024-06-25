package bitcamp.myapp.command;

import bitcamp.myapp.vo.User;

public class UserList extends LinkedList {

  public User findByNo(int userNo) {
    for (int i = 0; i < size(); i++) {
      User user = (User) getValue(i);
      if (user.getNo() == userNo) {
        return user;
      }
    }
    return null;
  }
}
