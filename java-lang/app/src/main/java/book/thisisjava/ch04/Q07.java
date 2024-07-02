package book.thisisjava.ch04;

import java.util.Scanner;

public class Q07 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int deposit = 10000;
    int withdrawal = 2000;
    int balance = deposit - withdrawal;

    printMenu();

    String command;
    loop: while (true) {
      System.out.printf("선택> ");
      command = scanner.nextLine();

      switch (command) {
        case "1":
          System.out.printf("예금액>%d\n", deposit);
          break;
        case "2":
          System.out.printf("출근액>%d\n", withdrawal);
          break;
        case "3":
          System.out.printf("잔고>%d\n", balance);
          break;
        case "4":
          break loop;
        default:
          System.out.println("올바른 메뉴 번호를 입력해주세요.");
      }

      System.out.println();
      printMenu();
    }

    System.out.println("프로그램 종료");
  }

  static void printMenu() {
    String line = "-------------------------";
    String[] menus = { "예금", "출금", "잔고", "종료" };

    System.out.println(line);
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("%d.%s", (i + 1), menus[i]);
      if (i < menus.length - 1) {
        System.out.printf(" | ");
      } else {
        System.out.println();
      }
    }
    System.out.println(line);
  }
}
