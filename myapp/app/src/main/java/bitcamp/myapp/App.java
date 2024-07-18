package bitcamp.myapp;

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
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {

  MenuGroup mainMenu = new MenuGroup("메인");

  List<User> userList = new ArrayList<>();
  List<Project> projectList = new LinkedList<>();
  List<Board> boardList = new LinkedList<>();

  public App() {

    MenuGroup userMenu = new MenuGroup(("회원"));
    userMenu.add(new MenuItem("등록", new UserAddCommand(userList)));
    userMenu.add(new MenuItem("목록", new UserListCommand(userList)));
    userMenu.add(new MenuItem("조회", new UserViewCommand(userList)));
    userMenu.add(new MenuItem("변경", new UserUpdateCommand(userList)));
    userMenu.add(new MenuItem("삭제", new UserDeleteCommand(userList)));
    mainMenu.add(userMenu);

    MenuGroup projectMenu = new MenuGroup(("프로젝트"));
    ProjectMemberHandler memberHandler = new ProjectMemberHandler(userList);
    projectMenu.add(new MenuItem("등록", new ProjectAddCommand(projectList, memberHandler)));
    projectMenu.add(new MenuItem("목록", new ProjectListCommand(projectList)));
    projectMenu.add(new MenuItem("조회", new ProjectViewCommand(projectList)));
    projectMenu.add(new MenuItem("변경", new ProjectUpdateCommand(projectList, memberHandler)));
    projectMenu.add(new MenuItem("삭제", new ProjectDeleteCommand(projectList)));
    mainMenu.add(projectMenu);

    MenuGroup boardMenu = new MenuGroup(("게시판"));
    boardMenu.add(new MenuItem("등록", new BoardAddCommand(boardList)));
    boardMenu.add(new MenuItem("목록", new BoardListCommand(boardList)));
    boardMenu.add(new MenuItem("조회", new BoardViewCommand(boardList)));
    boardMenu.add(new MenuItem("변경", new BoardUpdateCommand(boardList)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteCommand(boardList)));
    mainMenu.add(boardMenu);

    mainMenu.add(new MenuItem("도움말", new HelpCommand()));
    mainMenu.add(new MenuItem("명령내역", new HistoryCommand()));

    mainMenu.setExitMenuTitle("종료");
  }


  public static void main(String[] args) {
    new App().execute();
  }

  void execute() {
    String appTitle = "[프로젝트 관리 시스템]";
    String line = "----------------------------------";

    try {
      loadData();
      mainMenu.execute();
    } catch (Exception ex) {
      System.out.println("실행 오류!");
    } finally {
      saveData();
    }

    System.out.println("종료합니다.");
    Prompt.close();
  }

  private void loadData() {
    loadUsers();
    loadProjects();
    loadBoards();
    System.out.println("데이터를 로딩했습니다.");
  }

  private void loadUsers() {
    try (FileInputStream in = new FileInputStream("user.data")) {
      // user 데이터 개수 : 파일에서 2바이트를 읽는다.
      int userLength = (in.read() << 8) | in.read();
      int maxUserNo = 0;
      int len;
      byte[] bytes;

      for (int i = 0; i < userLength; i++) {
        // 한 개의 User 데이터 바이트 배열 크기 : 파일에서 2바이트를 읽는다.
        len = (in.read() << 8) | in.read();

        // 한 개의 User 데이터 바이트 배열 : 위에서 지정한 개수만큼 바이트 배열을 읽는다.
        bytes = new byte[len];
        in.read(bytes);

        // User 바이트 배열을 가지고 User 객체를 생성
        User user = User.valueOf(bytes);
        userList.add(user);

        if (user.getNo() > maxUserNo) {
          maxUserNo = user.getNo();
        }
      }

      User.initSeqNo(maxUserNo);

    } catch (IOException e) {
      System.out.println("회원 정보 로딩 중 오류 발생!");
    }
  }

  private void loadProjects() {
    try (FileInputStream in = new FileInputStream("project.data")) {
      int projectLength = in.read() << 8 | in.read();
      int maxProjectNo = 0;
      int len;
      byte[] bytes;

      for (int i = 0; i < projectLength; i++) {
        len = in.read() << 8 | in.read();
        bytes = new byte[len];
        in.read(bytes);

        Project project = Project.valueOf(bytes);
        projectList.add(project);

        if (project.getNo() > maxProjectNo) {
          maxProjectNo = project.getNo();
        }
      }

      Project.initSeqNo(maxProjectNo);
    } catch (IOException e) {
      System.out.println("게시판 정보 로딩 중 오류 발생!");
    }
  }

  private void loadBoards() {
    try (FileInputStream in = new FileInputStream("board.data")) {
      int boardLength = in.read() << 8 | in.read();
      int maxBoardNo = 0;
      int len;
      byte[] bytes;

      for (int i = 0; i < boardLength; i++) {
        len = in.read() << 8 | in.read();
        bytes = new byte[len];
        in.read(bytes);

        Board board = Board.valueOf(bytes);
        boardList.add(board);

        if (board.getNo() > maxBoardNo) {
          maxBoardNo = board.getNo();
        }
      }

      Board.initSeqNo(maxBoardNo);
    } catch (IOException e) {
      System.out.println("게시판 정보 로딩 중 오류 발생!");
    }
  }

  private void saveData() {
    saveUsers();
    saveProjects();
    saveBoards();
    System.out.println("데이터를 저장했습니다.");
  }

  private void saveUsers() {
    try (FileOutputStream out = new FileOutputStream("user.data");) {
      // 몇 개의 데이터르 읽을지 알려주기 위해 저장 데이터의 개수를 출력
      out.write(userList.size() >> 8);
      out.write(userList.size());
      byte[] bytes;

      for (User user : userList) {
        bytes = user.getBytes();
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
      }
    } catch (IOException e) {
      System.out.println("회원 정보 저장 중 오류 발생!");
    }
  }

  private void saveProjects() {
    try (FileOutputStream out = new FileOutputStream("project.data")) {
      out.write(projectList.size() >> 8);
      out.write(projectList.size());
      byte[] bytes;

      for (Project project : projectList) {
        bytes = project.getBytes();
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
      }

    } catch (IOException e) {
      System.out.println("프로젝트 정보 저장 중 오류 발생!");
    }
  }

  private void saveBoards() {
    try (FileOutputStream out = new FileOutputStream("board.data")) {
      out.write(boardList.size() >> 8);
      out.write(boardList.size());
      byte[] bytes;

      for (Board board : boardList) {
        bytes = board.getBytes();
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
      }

    } catch (IOException e) {
      System.out.println("게시판 정보 저장 중 오류 발생!");
    }
  }
}
