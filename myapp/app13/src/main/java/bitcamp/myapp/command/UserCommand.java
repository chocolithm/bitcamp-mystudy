package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.User;

public class UserCommand {

  UserList userList = new UserList();

  public void executeUserCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        this.addUser();
        break;
      case "조회":
        this.viewUser();
        break;
      case "목록":
        this.listUser();
        break;
      case "변경":
        this.updateUser();
        break;
      case "삭제":
        this.deleteUser();
        break;
    }
  }

  private void addUser() {
    User user = new User();
    user.setName(Prompt.input("이름?"));
    user.setEmail(Prompt.input("이메일?"));
    user.setPassword(Prompt.input("암호?"));
    user.setTel(Prompt.input("연락처?"));
    user.setNo(User.getNextSeqNo());
    this.userList.add(user);
  }

  private void listUser() {
    System.out.println("번호 이름 이메일");
    for (User user : this.userList.toArray()) {
      System.out.printf("%d %s %s\n", user.getNo(), user.getName(), user.getEmail());
    }
  }

  private void viewUser() {
    int userNo = Prompt.inputInt("회원번호?");
    User user = this.userList.findByNo(userNo);
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }

    System.out.printf("이름: %s\n", user.getName());
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("연락처: %s\n", user.getTel());
  }

  private void updateUser() {
    int userNo = Prompt.inputInt("회원번호?");
    User user = this.userList.findByNo(userNo);
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }

    user.setName(Prompt.input("이름(%s)?", user.getName()));
    user.setEmail(Prompt.input("이메일(%s)?", user.getEmail()));
    user.setPassword(Prompt.input("암호?"));
    user.setTel(Prompt.input("연락처(%s)?", user.getTel()));
    System.out.println("변경했습니다.");
  }

  private void deleteUser() {
    int userNo = Prompt.inputInt("회원번호?");
    User user = this.userList.delete(userNo);
    if (user != null) {
      System.out.printf("'%s' 회원을 삭제했습니다.", user.getNo());
    } else {
      System.out.println("없는 회원입니다.");
    }
  }

  public UserList getUserList() {
    return this.userList;
  }
}
