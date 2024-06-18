package bitcamp.myapp;

import java.util.Scanner;

public class App04 {

  static Scanner keyboardScanner = new Scanner(System.in);
  static String[] mainMenus = new String[] {"회원", "팀", "프로젝트", "게시판", "도움말", "종료"};
  static String[][] subMenus = {
      {"등록", "목록", "조회", "변경", "삭제"},
      {"등록", "목록", "조회", "변경", "삭제"},
      {"등록", "목록", "조회", "변경", "삭제"},
      {"등록", "목록", "조회", "변경", "삭제"}
  };

  static final int MAX_SIZE = 100;
  static String[] name = new String[MAX_SIZE];
  static String[] email = new String[MAX_SIZE];
  static String[] password = new String[MAX_SIZE];
  static String[] tel = new String[MAX_SIZE];
  static int memberLength = 0;

  public static void main(String[] args) {

    printMenu(); // 메서드에 묶인 코드를 실행하는 것을 "메서드를 호출(call)한다"라고 부른다.

    String command;
    while (true) {
      try {
        command = prompt("메인>");

        if (command.equals("menu")) {
          printMenu();

        } else {
          int menuNo = Integer.parseInt(command);
          String menuTitle = getMenuTitle(menuNo, mainMenus); // 설명하는 변수
          if (menuTitle == null) {
            System.out.println("유효한 메뉴 번호가 아닙니다.");
          } else if (menuTitle.equals("종료")) {
            break;
          } else {
            if (menuNo >= 1 && menuNo <= 4) {
              processMenu(menuTitle, subMenus[menuNo - 1]);
            } else {
              System.out.println(menuTitle);
            }
          }
        }
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }

    System.out.println("종료합니다.");

    keyboardScanner.close();
  }

  static void printMenu() {
    String boldAnsi = "\033[1m";
    String redAnsi = "\033[31m";
    String resetAnsi = "\033[0m";

    String appTitle = "[팀 프로젝트 관리 시스템]";
    String line = "----------------------------------";

    System.out.println(boldAnsi + line + resetAnsi);
    System.out.println(boldAnsi + appTitle + resetAnsi);

    for (int i = 0; i < mainMenus.length; i++) {
      if (mainMenus[i].equals("종료")) {
        System.out.printf("%s%d. %s%s\n", (boldAnsi + redAnsi), (i + 1), mainMenus[i], resetAnsi);
      } else {
        System.out.printf("%d. %s\n", (i + 1), mainMenus[i]);
      }
    }

    System.out.println(boldAnsi + line + resetAnsi);
  }

  static void printSubMenu(String menuTitle, String[] menus) {
    System.out.printf("[%s]\n", menuTitle);
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("%d. %s\n", (i + 1), menus[i]);
    }
    System.out.println("9. 이전");
  }

  static String prompt(String title) {
    System.out.printf("%s ", title);
    return keyboardScanner.nextLine();
  }

  static boolean isValidateMenu(int menuNo, String[] menus) {
    return menuNo >= 1 && menuNo <= menus.length;
  }

  static String getMenuTitle(int menuNo, String[] menus) {
    return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
  }

  static void processMenu(String menuTitle, String[] menus) {
    printSubMenu(menuTitle, menus);
    while (true) {
      String command = prompt(String.format("메인/%s>", menuTitle));
      if (command.equals("menu")) {
        printSubMenu(menuTitle, menus);
        continue;
      } else if (command.equals("9")) { // 이전 메뉴 선택
        break;
      }

      try {
        int menuNo = Integer.parseInt(command);
        String subMenuTitle = getMenuTitle(menuNo, menus);
        if (subMenuTitle == null) {
          System.out.println("유효한 메뉴 번호가 아닙니다.");
        } else {
          switch (menuTitle) {
            case "회원":
              executeUserCommand(subMenuTitle);
              break;
            case "팀":
              executeTeamCommand(subMenuTitle);
              break;
            case "프로젝트":
              executeProjectCommand(subMenuTitle);
              break;
            case "게시판":
              executeBoardCommand(subMenuTitle);
              break;
            default:
              System.out.printf("%s 메뉴에 대한 명령을 처리할 수 없습니다.\n", menuTitle);
          }
        }
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }

  static void executeUserCommand(String command) {
    System.out.printf("[%s]\n", command);

    switch (command) {
      case "등록":
        addUser();
        break;
      case "조회":
        viewUser();
        break;
      case "목록":
        listUser();
        break;
      case "변경":
        updateUser();
        break;
      case "삭제":
        deleteUser();
        break;
    }
  }

  static void addUser() {
    name[memberLength] = prompt("이름? ");
    email[memberLength] = prompt("이메일? ");
    password[memberLength] = prompt("암호? ");
    tel[memberLength] = prompt("연락처? ");
    memberLength++;
  }

  static void listUser() {
    System.out.println("번호 이름 이메일");
    for (int i = 0; i < memberLength; i++) {
      System.out.printf("%d %s %s\n", (i + 1), name[i], email[i]);
    }
  }

  static void viewUser() {
    int userNo = Integer.parseInt(prompt("회원번호?"));
    if (userNo < 1 || userNo > memberLength) {
      System.out.println("없는 회원입니다.");
      return;
    }
    System.out.printf("이름: %s\n", name[userNo - 1]);
    System.out.printf("이메일: %s\n", email[userNo - 1]);
    System.out.printf("연락처: %s\n", tel[userNo - 1]);
  }

  static void updateUser() {
    int userNo = Integer.parseInt(prompt("회원번호?"));
    if (userNo < 1 || userNo > memberLength) {
      System.out.println("없는 회원입니다.");
      return;
    }
    name[userNo - 1] = prompt(String.format("이름(%s)? ", name[userNo - 1]));
    email[userNo - 1] = prompt(String.format("이메일(%s)? ", email[userNo - 1]));
    password[userNo - 1] = prompt("암호? ");
    tel[userNo - 1] = prompt(String.format("연락처(%s)?", tel[userNo - 1]));
    System.out.println("변경했습니다.");
  }

  static void deleteUser() {
    int userNo = Integer.parseInt(prompt("회원번호?"));
    if (userNo < 1 || userNo > memberLength) {
      System.out.println("없는 회원입니다.");
      return;
    }
    for (int i = userNo; i < memberLength; i++) {
      name[i - 1] = name[i];
      email[i - 1] = email[i];
      password[i - 1] = password[i];
      tel[i - 1] = tel[i];
    }
    memberLength--;
    name[memberLength] = null;
    email[memberLength] = null;
    password[memberLength] = null;
    tel[memberLength] = null;
  }

  static void executeTeamCommand(String command) {
    System.out.printf("팀 %s\n", command);
  }

  static void executeProjectCommand(String command) {
    System.out.printf("프로젝트 %s\n", command);
  }

  static void executeBoardCommand(String command) {
    System.out.printf("게시판 %s\n", command);
  }
}
