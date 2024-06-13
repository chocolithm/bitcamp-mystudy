package bitcamp.myapp;

import java.util.Scanner;

public class App {
    static Scanner keyboardScanner = new Scanner(System.in);
    static String[] menus = {"회원", "팀", "프로젝트", "게시판", "도움말", "종료"};
    static String[][] subMenus = {
            {"등록", "목록", "조회", "변경", "삭제"},
            {"등록", "목록", "조회", "변경", "삭제"},
            {"등록", "목록", "조회", "변경", "삭제"},
            {"등록", "목록", "조회", "변경", "삭제"}
    };

    public static void main(String[] args) {

        printMenu();

        String command;
        while (true) {
            try {
                command = prompt("메인");

                if(command.equals("menu")) {
                    printMenu();
                } else {
                    int menuNo = Integer.parseInt(command);
                    String menuTitle = getMenuTitle(menuNo, menus);

                    if(menuTitle == null) {
                        System.out.println("유효한 메뉴 번호가 아닙니다.");
                    } else if(menuTitle.equals("종료")) {
                        break;
                    } else {
                        processMenu(menuTitle, menuNo);
                    }
                }
            } catch (NumberFormatException e) {
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

        for(int i = 0; i < menus.length; i++) {
            if(menus[i].equals("종료")) {
                System.out.printf("%s%d. %s\n", (boldAnsi + redAnsi), (i + 1), (menus[i] + resetAnsi));
            } else {
                System.out.printf("%d. %s\n", (i + 1), menus[i]);
            }
        }

        System.out.println(boldAnsi + line + resetAnsi);
    }

    static void printSubMenu(String menuTitle, String[] menus) {
        System.out.printf("[%s]\n", menuTitle);
        for(int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), menus[i]);
        }
        System.out.println("9. 이전");
    }

    static String prompt(String promptTitle) {
        System.out.print(promptTitle + "> ");
        return keyboardScanner.nextLine();
    }

    static boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    static String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
    }

    static void processMenu(String menuTitle, int menuNo) {
        if(menuNo < 1 || menuNo > subMenus.length) {
            System.out.println(menuTitle);
        } else {
            printSubMenu(menuTitle, subMenus[menuNo - 1]);

            while(true) {
                String command = prompt("메인/" + menuTitle);

                if(command.equals("menu")) {
                    printSubMenu(menuTitle, subMenus[menuNo - 1]);
                } else if(command.equals("9")) {
                    break;
                } else {
                    try {
                        int subMenuNo = Integer.parseInt(command);
                        String subMenuTitle = getMenuTitle(subMenuNo, subMenus[menuNo - 1]);

                        if(subMenuTitle == null) {
                            System.out.println("유효한 메뉴 번호가 아닙니다.");
                        } else {
                            System.out.println(subMenuTitle);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("숫자로 메뉴 번호를 입력하세요.");
                    }
                }
            }
        }
    }
}
