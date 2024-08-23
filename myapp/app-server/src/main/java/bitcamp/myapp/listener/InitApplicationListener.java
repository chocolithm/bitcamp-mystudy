package bitcamp.myapp.listener;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.myapp.command.HelpCommand;
import bitcamp.myapp.command.board.BoardAddCommand;
import bitcamp.myapp.command.board.BoardDeleteCommand;
import bitcamp.myapp.command.board.BoardListCommand;
import bitcamp.myapp.command.board.BoardUpdateCommand;
import bitcamp.myapp.command.board.BoardViewCommand;
import bitcamp.myapp.command.project.ProjectAddCommand;
import bitcamp.myapp.command.project.ProjectDeleteCommand;
import bitcamp.myapp.command.project.ProjectListCommand;
import bitcamp.myapp.command.project.ProjectMemberHandler;
import bitcamp.myapp.command.project.ProjectUpdateCommand;
import bitcamp.myapp.command.project.ProjectViewCommand;
import bitcamp.myapp.command.user.UserAddCommand;
import bitcamp.myapp.command.user.UserDeleteCommand;
import bitcamp.myapp.command.user.UserListCommand;
import bitcamp.myapp.command.user.UserUpdateCommand;
import bitcamp.myapp.command.user.UserViewCommand;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.DaoFactory;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.mybatis.SqlSessionFactoryProxy;
import java.io.InputStream;
import java.sql.Connection;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class InitApplicationListener implements ApplicationListener {

  private Connection con;

  @Override
  public boolean onStartup(ApplicationContext ctx) throws Exception {

    InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
    SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);

    SqlSessionFactoryProxy sqlSessionFactoryProxy = new SqlSessionFactoryProxy(sqlSessionFactory);

    DaoFactory daoFactory = new DaoFactory(sqlSessionFactoryProxy);

    UserDao userDao = daoFactory.createObject(UserDao.class);
    BoardDao boardDao = daoFactory.createObject(BoardDao.class);
    ProjectDao projectDao = daoFactory.createObject(ProjectDao.class);

    ctx.setAttributes("userDao", userDao);
    ctx.setAttributes("boardDao", boardDao);
    ctx.setAttributes("projectDao", projectDao);

    MenuGroup mainMenu = ctx.getMainMenu();

    MenuGroup userMenu = new MenuGroup("회원");
    userMenu.add(new MenuItem("등록", new UserAddCommand(userDao, sqlSessionFactoryProxy)));
    userMenu.add(new MenuItem("목록", new UserListCommand(userDao)));
    userMenu.add(new MenuItem("조회", new UserViewCommand(userDao)));
    userMenu.add(new MenuItem("변경", new UserUpdateCommand(userDao, sqlSessionFactoryProxy)));
    userMenu.add(new MenuItem("삭제", new UserDeleteCommand(userDao, sqlSessionFactoryProxy)));
    mainMenu.add(userMenu);

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    ProjectMemberHandler memberHandler = new ProjectMemberHandler(userDao);
    projectMenu.add(
        new MenuItem("등록",
            new ProjectAddCommand(projectDao, memberHandler, sqlSessionFactoryProxy)));
    projectMenu.add(new MenuItem("목록", new ProjectListCommand(projectDao)));
    projectMenu.add(new MenuItem("조회", new ProjectViewCommand(projectDao)));
    projectMenu.add(
        new MenuItem("변경",
            new ProjectUpdateCommand(projectDao, memberHandler, sqlSessionFactoryProxy)));
    projectMenu.add(
        new MenuItem("삭제", new ProjectDeleteCommand(projectDao, sqlSessionFactoryProxy)));
    mainMenu.add(projectMenu);

    MenuGroup boardMenu = new MenuGroup("게시판");
    boardMenu.add(new MenuItem("등록", new BoardAddCommand(boardDao, sqlSessionFactoryProxy)));
    boardMenu.add(new MenuItem("목록", new BoardListCommand(boardDao)));
    boardMenu.add(new MenuItem("조회", new BoardViewCommand(boardDao, sqlSessionFactoryProxy)));
    boardMenu.add(new MenuItem("변경", new BoardUpdateCommand(boardDao, sqlSessionFactoryProxy)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteCommand(boardDao, sqlSessionFactoryProxy)));
    mainMenu.add(boardMenu);

    mainMenu.add(new MenuItem("도움말", new HelpCommand()));

    mainMenu.setExitMenuTitle("종료");

    return true;
  }
}
