package thisisjava.ch05;

import java.util.Scanner;

public class Q09 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String command;
    int[] scores = {};

    printMenu();

    loop:
      while (true) {
        System.out.print("선택> ");
        command = scanner.nextLine();

        switch(command) {
          case "1":
            System.out.printf("학생수> ");
            int studentCnt = Integer.parseInt(scanner.nextLine());
            scores = new int[studentCnt];
            break;
          case "2":
            if(scores.length == 0) {
              System.out.println("학생수를 먼저 입력하세요.");
              break;
            }

            for(int i = 0; i < scores.length; i++) {
              System.out.printf("scores[%d]> ", i);
              scores[i] = Integer.parseInt(scanner.nextLine());
            }
            break;
          case "3":
            for(int i = 0; i < scores.length; i++) {
              System.out.printf("scores[%d]: %d\n", i, scores[i]);
            }
            break;
          case "4":
            int max = scores[0];
            int sum = scores[0];
            for(int i = 1; i < scores.length; i++) {
              max = Math.max(max, scores[i]);
              sum += scores[i];
            }

            System.out.printf("최고 점수: %d\n", max);
            System.out.printf("평균 점수: %.1f\n", (float) sum / (float) scores.length);
            break;
          case "5":
            break loop;
        }

        printMenu();
      }

    System.out.println("프로그램 종료");
  }

  static void printMenu() {
    String line = "------------------";
    String[] menus = {"학생수", "점수입력", "점수리스트", "분석", "종료"};

    System.out.println(line);
    for(int i = 0; i < menus.length; i++) {
      System.out.printf("%d.%s", (i + 1), menus[i]);
      if(i < menus.length - 1) {
        System.out.printf(" | ");
      } else {
        System.out.println();
      }
    }
    System.out.println(line);
  }
}
