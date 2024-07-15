package bitcamp.myapp.command.user;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.User;
import bitcamp.util.util.Prompt;
import java.util.List;

public class UserUpdateCommand implements Command {

  private List<User> userList;

  public UserUpdateCommand(List<User> list) {
    this.userList = list;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    int userNo = Prompt.inputInt("회원번호?");
    int index = userList.indexOf(new User(userNo));
    if (index == -1) {
      System.out.println("없는 회원입니다.");
      return;
    }

    User user = userList.get(index);

    user.setName(Prompt.input("이름(%s)?", user.getName()));
    user.setEmail(Prompt.input("이메일(%s)?", user.getEmail()));
    user.setPassword(Prompt.input("암호?"));
    user.setTel(Prompt.input("연락처(%s)?", user.getTel()));
    System.out.println("변경 했습니다.");
  }
}
