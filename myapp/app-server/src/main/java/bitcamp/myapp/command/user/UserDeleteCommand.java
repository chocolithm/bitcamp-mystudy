package bitcamp.myapp.command.user;

import bitcamp.command.Command;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSession;

public class UserDeleteCommand implements Command {

  private UserDao userDao;
  private SqlSession sqlSession;

  public UserDeleteCommand(UserDao userDao, SqlSession sqlSession) {
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    prompt.printf("[%s]\n", menuName);

    try {
      int userNo = prompt.inputInt("회원번호?");
      User deletedUser = userDao.findBy(userNo);
      if (deletedUser == null) {
        prompt.println("없는 회원입니다.");
        return;
      }

      userDao.delete(userNo);
      sqlSession.commit();
      prompt.printf("'%s' 회원을 삭제 했습니다.\n", deletedUser.getName());

    } catch (Exception e) {
      sqlSession.rollback();
      prompt.println("회원 데이터 삭제 중 오류 발생!");
    }
  }
}
