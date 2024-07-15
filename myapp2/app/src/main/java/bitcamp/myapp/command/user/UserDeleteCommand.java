package bitcamp.myapp.command.user;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import java.util.List;

public class UserDeleteCommand implements Command {

  private List<User> userList;

  public UserDeleteCommand(List<User> list) {
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

    User deletedUser = userList.remove(index);
    System.out.printf("'%s' 회원을 삭제 했습니다.\n", deletedUser.getName());
  }
}
