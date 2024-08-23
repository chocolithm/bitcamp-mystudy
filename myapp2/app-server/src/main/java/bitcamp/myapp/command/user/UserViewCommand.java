package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;

public class UserViewCommand implements Command {

  private UserDao userDao;

  public UserViewCommand(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);
      int userNo = prompt.inputInt("회원번호?");

      User user = userDao.findBy(userNo);
      if (user == null) {
        prompt.println("없는 회원입니다.");
        return;
      }

      prompt.printf("이름: %s\n", user.getName());
      prompt.printf("이메일: %s\n", user.getEmail());
      prompt.printf("연락처: %s\n", user.getTel());

    } catch (Exception e) {
      prompt.println("조회 중 오류 발생!");
    }
  }
}
