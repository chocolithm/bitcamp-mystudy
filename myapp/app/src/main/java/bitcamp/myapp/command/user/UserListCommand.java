package bitcamp.myapp.command.user;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.User;
import java.util.List;

public class UserListCommand implements Command {

  private List<User> userList;

  public UserListCommand(List<User> list) {
    this.userList = list;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    System.out.println("번호 이름 이메일");
    for (User user : userList) {
      System.out.printf("%d %s %s\n", user.getNo(), user.getName(), user.getEmail());
    }
  }
}
