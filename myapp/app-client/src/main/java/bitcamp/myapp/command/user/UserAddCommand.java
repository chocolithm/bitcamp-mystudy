package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import org.apache.ibatis.session.SqlSession;

public class UserAddCommand implements Command {

  private UserDao userDao;
  private SqlSession sqlSession;

  public UserAddCommand(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    try {
      User user = new User();
      user.setName(Prompt.input("이름?"));
      user.setEmail(Prompt.input("이메일?"));
      user.setPassword(Prompt.input("암호?"));
      user.setTel(Prompt.input("연락처?"));

      userDao.insert(user);
      sqlSession.commit();
    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println("회원 등록 중 오류 발생!");
    }
  }
}
