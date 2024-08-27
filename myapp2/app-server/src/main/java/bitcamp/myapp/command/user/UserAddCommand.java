package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserAddCommand implements Command {

  private UserDao userDao;
  private SqlSessionFactory sqlSessionFactory;

  public UserAddCommand(UserDao userDao, SqlSessionFactory sqlSessionFactory) {

    this.userDao = userDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);
      User user = new User();
      user.setName(prompt.input("이름?"));
      user.setEmail(prompt.input("이메일?"));
      user.setPassword(prompt.input("암호?"));
      user.setTel(prompt.input("연락처?"));

      userDao.insert(user);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      prompt.println("등록 중 오류 발생!");
    }
  }
}
