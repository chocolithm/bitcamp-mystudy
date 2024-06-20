package bitcamp.myapp.command;

import bitcamp.myapp.vo.User;

public class UserList {

  private static final int MAX_SIZE = 100;
  private static User[] users = new User[MAX_SIZE];
  private static int userLength = 0;

  public static void add(User user) {
    users[userLength++] = user;
  }

  public static User delete(int userNo) {
    User deleteUser = findByNo(userNo);
    if (deleteUser == null) {
      return null;
    }

    int index = indexOf(deleteUser);
    for (int i = index + 1; i < userLength; i++) {
      users[i - 1] = users[i];
    }
    users[--userLength] = null;
    return deleteUser;
  }

  public static User[] toArray() {
    User[] arr = new User[userLength];
    for (int i = 0; i < userLength; i++) {
      arr[i] = users[i];
    }
    return arr;
  }

  public static User findByNo(int userNo) {
    for (int i = 0; i < userLength; i++) {
      if (users[i].getNo() == userNo) {
        return users[i];
      }
    }
    return null;
  }

  static int indexOf(User user) {
    for (int i = 0; i < userLength; i++) {
      if (users[i] == user) {
        return i;
      }
    }
    return -1;
  }
}
