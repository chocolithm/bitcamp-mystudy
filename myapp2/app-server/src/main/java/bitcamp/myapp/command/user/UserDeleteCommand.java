package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDeleteCommand implements Command {

  private UserDao userDao;
  private SqlSessionFactory sqlSessionFactory;

  public UserDeleteCommand(UserDao userDao, SqlSessionFactory sqlSessionFactory) {

    this.userDao = userDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    try {
      prompt.printf("[%s]\n", menuName);
      int userNo = prompt.inputInt("회원번호?");

      User deletedUser = userDao.findBy(userNo);
      if (deletedUser == null) {
        prompt.println("없는 회원입니다.");
        return;
      }

      userDao.delete(userNo);
      sqlSessionFactory.openSession(false).commit();
      prompt.printf("'%s' 회원을 삭제 했습니다.\n", deletedUser.getName());

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      prompt.println("삭제 중 오류 발생!");
    }
  }
}
