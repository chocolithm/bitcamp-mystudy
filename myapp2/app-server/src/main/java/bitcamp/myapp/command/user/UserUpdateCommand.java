package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserUpdateCommand implements Command {

  private UserDao userDao;
  private SqlSessionFactory sqlSessionFactory;

  public UserUpdateCommand(UserDao userDao, SqlSessionFactory sqlSessionFactory) {

    this.userDao = userDao;
    this.sqlSessionFactory = sqlSessionFactory;
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

      user.setName(prompt.input("이름(%s)?", user.getName()));
      user.setEmail(prompt.input("이메일(%s)?", user.getEmail()));
      user.setPassword(prompt.input("암호?"));
      user.setTel(prompt.input("연락처(%s)?", user.getTel()));

      userDao.update(user);
      sqlSessionFactory.openSession(false).commit();
      prompt.println("변경 했습니다.");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      prompt.println("변경 중 오류 발생!");
    }
  }

}
