package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;

public class UserListCommand implements Command {

  private UserDao userDao;

  public UserListCommand(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);

      prompt.println("번호 이름 이메일");

      for (User user : userDao.list()) {
        prompt.printf("%d %s %s\n", user.getNo(), user.getName(), user.getEmail());
      }

    } catch (Exception e) {
      prompt.println("목록 조회 중 오류 발생!");
    }
  }
}
