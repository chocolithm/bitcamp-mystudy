package bitcamp.myapp;

import java.util.Scanner;

public class App {
    static Scanner keyboardScanner = new Scanner(System.in);
    static String[] menus = new String[] {
            "회원",
            "팀",
            "프로젝트",
            "게시판",
            "도움말",
            "종료"
    };
    static String[] submenus = new String[] {
            "등록",
            "목록",
            "조회",
            "변경",
            "삭제",
            null,
            null,
            null,
            "이전"
    };
    static String promptHead = "메인";
    static int isSubmenu = 0;

    public static void main(String[] args) {

        printMenu();

        String command;
        while (true) {
            try {
                command = prompt();

                if (command.equals("menu")) {
                    printMenu();
                } else {
                    int menuNo = Integer.parseInt(command);
                    String menuTitle = getMenuTitle(menuNo, isSubmenu == 0 ? menus : submenus);

                    if (menuTitle == null) {
                        System.out.println("유효한 메뉴 번호가 아닙니다.");
                        
                    // menus 관련
                    } else if (menuTitle.equals("도움말")) {
                        System.out.println(menuTitle);
                    } else if (menuTitle.equals("종료")) {
                        break;
                    } else if(isSubmenu == 0) {
                        changeMenu(menuTitle);
                        
                    // submenus 관련
                    } else if (menuTitle.equals("이전")) {
                        changeMenu();
                    } else if(isSubmenu == 1) {
                        System.out.println(menuTitle);
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

        if(isSubmenu == 0) {
            System.out.println(boldAnsi + line + resetAnsi);
            System.out.println(boldAnsi + appTitle + resetAnsi);

            for (int i = 0; i < menus.length; i++) {
                if (menus[i].equals("종료")) {
                    System.out.printf("%s%d. %s%s\n", (boldAnsi + redAnsi), (i + 1), menus[i], resetAnsi);
                } else {
                    System.out.printf("%d. %s\n", (i + 1), menus[i]);
                }
            }
            System.out.println(boldAnsi + line + resetAnsi);

        } else if (isSubmenu == 1) {
            for (int i = 0; i < submenus.length; i++) {
                if(submenus[i] != null) {
                    System.out.printf("%d. %s\n", (i + 1), submenus[i]);
                }
            }
        }
    }

    static String prompt() {
        System.out.print(promptHead + "> ");
        return keyboardScanner.nextLine();
    }

    static boolean isValidate(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    static String getMenuTitle(int menuNo, String[] menus) {
        return isValidate(menuNo, menus) ? menus[menuNo - 1] : null;
    }

    static void changeMenu(String... menuTitle) {
        if(isSubmenu == 0) {
            System.out.println("[" + menuTitle[0] + "]");
            isSubmenu = 1;
            promptHead = "메인/" + menuTitle[0];
            printMenu();
        } else if (isSubmenu == 1) {
            isSubmenu = 0;
            promptHead = "메인";
        }
    }
}
