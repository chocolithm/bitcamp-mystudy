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
import bitcamp.myapp.vo.SequenceNo;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {

  MenuGroup mainMenu = new MenuGroup("메인");

  List<User> userList = new ArrayList<>();
  List<Project> projectList = new LinkedList<>();
  List<Board> boardList = new LinkedList<>();

  public App() {

    loadData();

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
      mainMenu.execute();
    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();
    } finally {
      saveData();
    }

    System.out.println("종료합니다.");
    Prompt.close();
  }

  private void loadData() {
    loadJson(userList, "user.json", User.class);
    loadJson(projectList, "project.json", Project.class);
    loadJson(boardList, "board.json", Board.class);

    System.out.println("데이터를 로딩했습니다.");
  }

  private <E> void loadJson(List<E> list, String filename, Class<E> elementType) {
    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

      StringBuilder strBuilder = new StringBuilder();
      String line;
      while ((line = in.readLine()) != null) {
        strBuilder.append(line);
      }

      list.addAll((List<E>) new GsonBuilder()
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .create()
          .fromJson(
              strBuilder.toString(),
              TypeToken.getParameterized(List.class, elementType)));

      // 읽어 들인 객체의 타입이 SequenceNo 구현체라면
      // 일련번호를 객체 식별 번호로 사용한다는 것이기 때문에
      // 목록에 저장된 객체 중에서 가장 큰 일련번호를 알아내어
      // 클래스의 스태틱 필드에 설정해야 한다.
      for (Class<?> type : elementType.getInterfaces()) {
        if (type == SequenceNo.class) {
          initSeqNo(list, elementType);
          break;
        }
      }

    } catch (Exception e) {
      System.out.printf("%s 정보 로딩 중 오류 발생!\n", filename);
      e.printStackTrace();
      userList = new ArrayList<>();
    }
  }

  private <E> void initSeqNo(List<E> list, Class<E> elementType) throws Exception {
    int maxSeqNo = 0;
    for (Object element : list) {
      SequenceNo seqObj = (SequenceNo) element;
      if (seqObj.getNo() > maxSeqNo) {
        maxSeqNo = seqObj.getNo();
      }
    }

    Method method = elementType.getMethod("initSeqNo", int.class);
    method.invoke(null, maxSeqNo);
    // == User.initSeqNo(maxSeqNo);
  }

  //  private void loadUsers() {
  //    try (BufferedReader in = new BufferedReader(new FileReader("user.json"))) {
  //
  //      StringBuilder strBuilder = new StringBuilder();
  //      String line;
  //      while ((line = in.readLine()) != null) {
  //        strBuilder.append(line);
  //      }
  //
  //      userList.addAll(new Gson().fromJson(
  //          strBuilder.toString(),
  //          new TypeToken<ArrayList<User>>() {
  //          }));
  //
  //      int maxUserNo = 0;
  //      for (User user : userList) {
  //        if (user.getNo() > maxUserNo) {
  //          maxUserNo = user.getNo();
  //        }
  //      }
  //      User.initSeqNo(maxUserNo);
  //
  //    } catch (IOException e) {
  //      System.out.println("회원 정보 로딩 중 오류 발생!");
  //      e.printStackTrace();
  //      userList = new ArrayList<>();
  //    }
  //  }
  //
  //  private void loadProjects() {
  //    try (BufferedReader in = new BufferedReader(new FileReader("project.json"))) {
  //
  //      StringBuilder strBuilder = new StringBuilder();
  //      String line;
  //      while ((line = in.readLine()) != null) {
  //        strBuilder.append(line);
  //      }
  //
  //      projectList.addAll(new Gson().fromJson(
  //          strBuilder.toString(),
  //          new TypeToken<LinkedList<Project>>() {
  //          }));
  //
  //      int maxProjectNo = 0;
  //      for (Project project : projectList) {
  //        if (project.getNo() > maxProjectNo) {
  //          maxProjectNo = project.getNo();
  //        }
  //      }
  //
  //      Project.initSeqNo(maxProjectNo);
  //    } catch (IOException e) {
  //      System.out.println("프로젝트 정보 로딩 중 오류 발생!");
  //      e.printStackTrace();
  //      projectList = new LinkedList<>();
  //    }
  //  }
  //
  //  private void loadBoards() {
  //    try (BufferedReader in = new BufferedReader(new FileReader("board.json"))) {
  //
  //      StringBuilder strBuilder = new StringBuilder();
  //      String line;
  //
  //      while ((line = in.readLine()) != null) {
  //        strBuilder.append(line);
  //      }
  //
  //      boardList.addAll(new GsonBuilder()
  //          .setDateFormat("yyyy-MM-dd HH:mm:ss")
  //          .create()
  //          .fromJson(
  //              strBuilder.toString(),
  //              new TypeToken<LinkedList<Board>>() {
  //              }));
  //
  //
  //      int maxBoardNo = 0;
  //      for (Board board : boardList) {
  //        if (board.getNo() > maxBoardNo) {
  //          maxBoardNo = board.getNo();
  //        }
  //      }
  //
  //      Board.initSeqNo(maxBoardNo);
  //    } catch (IOException e) {
  //      System.out.println("게시판 정보 로딩 중 오류 발생!");
  //      e.printStackTrace();
  //      boardList = new LinkedList<>();
  //    }
  //  }

  private void saveData() {
    saveJson(userList, "user.json");
    saveJson(projectList, "project.json");
    saveJson(boardList, "board.json");
    System.out.println("데이터를 저장했습니다.");
  }

  private void saveJson(Object obj, String filename) {
    try (FileWriter out = new FileWriter(filename)) {

      out.write(new GsonBuilder()
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .create()
          .toJson(obj));

    } catch (IOException e) {
      System.out.printf("%s 정보 저장 중 오류 발생!\n", filename);
      e.printStackTrace();
    }
  }

  //  private void saveUsers() {
  //    try (FileWriter out = new FileWriter("user.json")) {
  //
  //      out.write(new Gson().toJson(userList));
  //
  //    } catch (IOException e) {
  //      System.out.println("회원 정보 저장 중 오류 발생!");
  //      e.printStackTrace();
  //    }
  //  }
  //
  //  private void saveProjects() {
  //    try (FileWriter out = new FileWriter("project.json")) {
  //
  //      out.write(new Gson().toJson(projectList));
  //
  //    } catch (IOException e) {
  //      System.out.println("프로젝트 정보 저장 중 오류 발생!");
  //      e.printStackTrace();
  //    }
  //  }
  //
  //  private void saveBoards() {
  //    try (FileWriter out = new FileWriter("board.json")) {
  //
  //      out.write(new GsonBuilder()
  //          .setDateFormat("yyyy-MM-dd HH:mm:ss")
  //          .create()
  //          .toJson(boardList));
  //
  //    } catch (IOException e) {
  //      System.out.println("게시판 정보 저장 중 오류 발생!");
  //      e.printStackTrace();
  //    }
  //  }
}
