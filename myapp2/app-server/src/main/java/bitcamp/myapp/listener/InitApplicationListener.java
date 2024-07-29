package bitcamp.myapp.listener;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.ListBoardDao;
import bitcamp.myapp.dao.ListProjectDao;
import bitcamp.myapp.dao.ListUserDao;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.dao.skel.BoardDaoSkel;
import bitcamp.myapp.dao.skel.ProjectDaoSkel;
import bitcamp.myapp.dao.skel.UserDaoSkel;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InitApplicationListener implements ApplicationListener {
  UserDao userDao;
  ProjectDao projectDao;
  BoardDao boardDao;

  @Override
  public void onStartup(ApplicationContext context) {
    userDao = new ListUserDao();
    projectDao = new ListProjectDao(userDao);
    boardDao = new ListBoardDao();

    context.setAttributes("userDaoSkel", new UserDaoSkel(userDao));
    context.setAttributes("projectDaoSkel", new ProjectDaoSkel(projectDao));
    context.setAttributes("boardDaoSkel", new BoardDaoSkel(boardDao));
  }

  @Override
  public void onShutdown(ApplicationContext context) {
    try (XSSFWorkbook workbook = new XSSFWorkbook()) {
      ((ListUserDao) userDao).save(workbook);
      ((ListProjectDao) projectDao).save(workbook);
      ((ListBoardDao) boardDao).save(workbook);

      try (FileOutputStream out = new FileOutputStream("data.xlsx")) {
        workbook.write(out);
      }
      System.out.println("데이터를 저장 했습니다.");
    } catch (Exception e) {
      System.out.println("데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
