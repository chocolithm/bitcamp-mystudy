package bitcamp.myapp.command;

import bitcamp.myapp.vo.User;

public class UserList {

  private static final int MAX_SIZE = 100;
  private User[] users = new User[MAX_SIZE];
  private int userLength = 0;

  public void add(User user) {
    this.users[this.userLength++] = user;
  }

  public User delete(int userNo) {
    User deleteUser = findByNo(userNo);
    if (deleteUser == null) {
      return null;
    }

    int index = indexOf(deleteUser);
    for (int i = index + 1; i < this.userLength; i++) {
      this.users[i - 1] = this.users[i];
    }
    this.users[--this.userLength] = null;
    return deleteUser;
  }

  public User[] toArray() {
    User[] arr = new User[this.userLength];
    for (int i = 0; i < this.userLength; i++) {
      arr[i] = this.users[i];
    }
    return arr;
  }

  public User findByNo(int userNo) {
    for (int i = 0; i < this.userLength; i++) {
      if (this.users[i].getNo() == userNo) {
        return this.users[i];
      }
    }
    return null;
  }

  public int indexOf(User user) {
    for (int i = 0; i < this.userLength; i++) {
      if (this.users[i] == user) {
        return i;
      }
    }
    return -1;
  }
}
