package bitcamp.myapp.command;

import bitcamp.myapp.vo.User;

public class UserList extends ArrayList {

  public User findByNo(int userNo) {
    for (int i = 0; i < size(); i++) {
      User user = (User) get(i);
      if (user.getNo() == userNo) {
        return user;
      }
    }
    return null;
  }
}
