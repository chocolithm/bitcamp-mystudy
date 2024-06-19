package bitcamp.myapp;

import bitcamp.myapp.command.BoardCommand;
import bitcamp.myapp.command.ProjectCommand;
import bitcamp.myapp.command.UserCommand;
import bitcamp.myapp.util.Prompt;

public class App {


  static String[] mainMenus = new String[] {"회원", "프로젝트", "게시판", "도움말", "종료"};
  static String[][] subMenus = {
      {"등록", "목록", "조회", "변경", "삭제"},
      {"등록", "목록", "조회", "변경", "삭제"},
      {"등록", "목록", "조회", "변경", "삭제"}
  };


  public static void main(String[] args) {

    printMenu(); // 메서드에 묶인 코드를 실행하는 것을 "메서드를 호출(call)한다"라고 부른다.

    String command;
    while (true) {
      try {
        command = Prompt.input("메인>");

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
            if (menuNo >= 1 && menuNo <= 3) {
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

    Prompt.close();
  }

  static void printMenu() {
    String boldAnsi = "\033[1m";
    String redAnsi = "\033[31m";
    String resetAnsi = "\033[0m";

    String appTitle = "[프로젝트 관리 시스템]";
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

  static boolean isValidateMenu(int menuNo, String[] menus) {
    return menuNo >= 1 && menuNo <= menus.length;
  }

  static String getMenuTitle(int menuNo, String[] menus) {
    return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
  }

  static void processMenu(String menuTitle, String[] menus) {
    printSubMenu(menuTitle, menus);
    while (true) {
      String command = Prompt.input(String.format("메인/%s>", menuTitle));
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
              UserCommand.executeUserCommand(subMenuTitle);
              break;
            case "프로젝트":
              ProjectCommand.executeProjectCommand(subMenuTitle);
              break;
            case "게시판":
              BoardCommand.executeBoardCommand(subMenuTitle);
              break;
            default:
              System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
          }
        }
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }
}
