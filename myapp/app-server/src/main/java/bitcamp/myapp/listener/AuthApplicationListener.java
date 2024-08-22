package bitcamp.myapp.listener;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;

public class AuthApplicationListener implements ApplicationListener {

  @Override
  public boolean onStartup(ApplicationContext ctx) throws Exception {
    String email = "";//Prompt.input("이메일?");
    String password = "";//Prompt.input("암호?");

    UserDao userDao = (UserDao) ctx.getAttribute("userDao");

    User user = userDao.findByEmailAndPassword(email, password);
    //    if (user == null) {
    //      System.out.println("로그인 실패!");
    //      return false;
    //    }

    ctx.setAttributes("loginUser", user);
    return true;
  }
}
