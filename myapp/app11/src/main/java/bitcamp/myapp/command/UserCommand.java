package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.User;

public class UserCommand {

  private static final int MAX_SIZE = 100;
  private static User[] users = new User[MAX_SIZE];
  private static int userLength = 0;

  public static void executeUserCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        addUser();
        break;
      case "조회":
        viewUser();
        break;
      case "목록":
        listUser();
        break;
      case "변경":
        updateUser();
        break;
      case "삭제":
        deleteUser();
        break;
    }
  }

  private static void addUser() {
    User user = new User();
    user.setName(Prompt.input("이름?"));
    user.setEmail(Prompt.input("이메일?"));
    user.setPassword(Prompt.input("암호?"));
    user.setTel(Prompt.input("연락처?"));
    user.setNo(User.getNextSeqNo());
    users[userLength++] = user;
  }

  private static void listUser() {
    System.out.println("번호 이름 이메일");
    for (int i = 0; i < userLength; i++) {
      User user = users[i];
      System.out.printf("%d %s %s\n", user.getNo(), user.getName(), user.getEmail());
    }
  }

  private static void viewUser() {
    int userNo = Prompt.inputInt("회원번호?");
    User user = findByNo(userNo);
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }
    System.out.printf("이름: %s\n", user.getName());
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("연락처: %s\n", user.getTel());
  }

  private static void updateUser() {
    int userNo = Prompt.inputInt("회원번호?");
    User user = findByNo(userNo);
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }
    user.setName(Prompt.input("이름(%s)?", user.getName()));
    user.setEmail(Prompt.input("이메일(%s)?", user.getEmail()));
    user.setPassword(Prompt.input("암호?"));
    user.setTel(Prompt.input("연락처(%s)?", user.getTel()));
    System.out.println("변경 했습니다.");
  }

  private static void deleteUser() {
    int userNo = Prompt.inputInt("회원번호?");
    User user = findByNo(userNo);
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }
    int index = indexOf(user);
    for (int i = index + 1; i < userLength; i++) {
      users[i - 1] = users[i];
    }
    users[--userLength] = null;
    System.out.println("삭제 했습니다.");
  }

  public static User findByNo(int userNo) {
    for (int i = 0; i < userLength; i++) {
      User user = users[i];
      if (userNo == user.getNo()) {
        return user;
      }
    }
    return null;
  }

  public static int indexOf(User user) {
    for (int i = 0; i < userLength; i++) {
      if (users[i] == user) {
        return i;
      }
    }
    return -1;
  }
}
