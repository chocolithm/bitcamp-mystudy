package bitcamp.myapp.listener;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.myapp.command.HelpCommand;
import bitcamp.myapp.command.HistoryCommand;
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
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.dao.mysql.BoardDaoImpl;
import bitcamp.myapp.dao.mysql.ProjectDaoImpl;
import bitcamp.myapp.dao.mysql.UserDaoImpl;
import java.io.InputStream;
import java.sql.Connection;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class InitApplicationListener implements ApplicationListener {

  Connection con;

  @Override
  public boolean onStart(ApplicationContext ctx) throws Exception {

    InputStream inputStream = Resources.getResourceAsStream("config/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
        new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession(false);

    UserDao userDao = new UserDaoImpl(sqlSession);
    ProjectDao projectDao = new ProjectDaoImpl(sqlSession);
    BoardDao boardDao = new BoardDaoImpl(sqlSession);

    ctx.setAttribute("userDao", userDao);
    ctx.setAttribute("boardDao", boardDao);
    ctx.setAttribute("projectDao", projectDao);

    MenuGroup mainMenu = ctx.getMainMenu();

    MenuGroup userMenu = new MenuGroup("회원");
    userMenu.add(new MenuItem("등록", new UserAddCommand(userDao, sqlSession)));
    userMenu.add(new MenuItem("목록", new UserListCommand(userDao)));
    userMenu.add(new MenuItem("조회", new UserViewCommand(userDao)));
    userMenu.add(new MenuItem("변경", new UserUpdateCommand(userDao, sqlSession)));
    userMenu.add(new MenuItem("삭제", new UserDeleteCommand(userDao, sqlSession)));
    mainMenu.add(userMenu);

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    ProjectMemberHandler memberHandler = new ProjectMemberHandler(userDao);
    projectMenu.add(
        new MenuItem("등록", new ProjectAddCommand(projectDao, memberHandler, sqlSession)));
    projectMenu.add(new MenuItem("목록", new ProjectListCommand(projectDao)));
    projectMenu.add(new MenuItem("조회", new ProjectViewCommand(projectDao)));
    projectMenu.add(
        new MenuItem("변경", new ProjectUpdateCommand(projectDao, memberHandler, sqlSession)));
    projectMenu.add(new MenuItem("삭제", new ProjectDeleteCommand(projectDao, sqlSession)));
    mainMenu.add(projectMenu);

    MenuGroup boardMenu = new MenuGroup("게시판");
    boardMenu.add(new MenuItem("등록", new BoardAddCommand(boardDao, ctx, sqlSession)));
    boardMenu.add(new MenuItem("목록", new BoardListCommand(boardDao)));
    boardMenu.add(new MenuItem("조회", new BoardViewCommand(boardDao, sqlSession)));
    boardMenu.add(new MenuItem("변경", new BoardUpdateCommand(boardDao, ctx, sqlSession)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteCommand(boardDao, ctx, sqlSession)));
    mainMenu.add(boardMenu);

    mainMenu.add(new MenuItem("도움말", new HelpCommand()));
    mainMenu.add(new MenuItem("명령내역", new HistoryCommand()));

    mainMenu.setExitMenuTitle("종료");

    return true;
  }
}
