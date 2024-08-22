package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSession;

public class UserUpdateCommand implements Command {

  private UserDao userDao;
  private SqlSession sqlSession;

  public UserUpdateCommand(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    prompt.printf("[%s]\n", menuName);

    try {
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
      sqlSession.commit();
      prompt.println("변경 했습니다.");
    } catch (Exception e) {
      sqlSession.rollback();
      prompt.println("회원 데이터 변경 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
