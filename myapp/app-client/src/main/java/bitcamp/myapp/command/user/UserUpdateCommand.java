package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import org.apache.ibatis.session.SqlSession;

public class UserUpdateCommand implements Command {

  private UserDao userDao;
  private SqlSession sqlSession;

  public UserUpdateCommand(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    try {
      int userNo = Prompt.inputInt("회원번호?");
      User user = userDao.findBy(userNo);
      if (user == null) {
        System.out.println("없는 회원입니다.");
        return;
      }

      user.setName(Prompt.input("이름(%s)?", user.getName()));
      user.setEmail(Prompt.input("이메일(%s)?", user.getEmail()));
      user.setPassword(Prompt.input("암호?"));
      user.setTel(Prompt.input("연락처(%s)?", user.getTel()));

      userDao.update(user);
      sqlSession.commit();
      System.out.println("변경 했습니다.");
    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println("회원 데이터 변경 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
