package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSession;

public class UserAddCommand implements Command {

  private UserDao userDao;
  private SqlSession sqlSession;

  public UserAddCommand(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    prompt.printf("[%s]\n", menuName);

    try {
      User user = new User();
      user.setName(prompt.input("이름?"));
      user.setEmail(prompt.input("이메일?"));
      user.setPassword(prompt.input("암호?"));
      user.setTel(prompt.input("연락처?"));

      userDao.insert(user);
      sqlSession.commit();
    } catch (Exception e) {
      sqlSession.rollback();
      prompt.println("회원 등록 중 오류 발생!");
    }
  }
}
