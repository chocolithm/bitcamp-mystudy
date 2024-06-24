package thisisjava.ch06.q20;

import java.util.Scanner;

public class BankApplication {
  public static void main(String[] args) {
    Account[] accounts = new Account[100];
    int accountLength = 0;
    BankApplication operator = new BankApplication();
    Scanner scanner = new Scanner(System.in);
    String[] menus = {"계좌생성", "계좌목록", "예금", "출금", "종료"};

    operator.printMenu(menus);

    String command;
    int menuNo;
    String menu;
    String accountNo;
    Account account;

    loop:
      while(true) {
        command = operator.prompt(scanner, "선택>");
        try {
          menuNo = Integer.parseInt(command);
        } catch(Exception e) {
          System.out.println("숫자를 입력해주세요.");
          continue;
        }
        menu = menus[menuNo - 1];

        operator.printTitle(menuNo, menus);

        switch(menu) {
          case "계좌생성":
            account = new Account();
            account.setAccountNo(operator.prompt(scanner, "계좌번호:"));
            account.setOwner(operator.prompt(scanner, "계좌주:"));
            account.setBalance(Integer.parseInt(operator.prompt(scanner, "초기입금액:")));

            accounts[accountLength++] = account;
            System.out.println("결과: 계좌가 생성되었습니다.");
            break;
          case "계좌목록":
            for(int i = 0; i < accountLength; i++) {
              System.out.printf("%s\t%s\t%s\n",
                  accounts[i].getAccountNo(), accounts[i].getOwner(), accounts[i].getBalance());
            }
            break;
          case "예금":
            accountNo = operator.prompt(scanner, "계좌번호:");
            account = new Account();
            for(int i = 0; i < accountLength; i++) {
              if(accounts[i].getAccountNo().equals(accountNo)) {
                account = accounts[i];
                break;
              }
            }
            account.setBalance(Integer.parseInt(operator.prompt(scanner, "예금액:")));
            break;
          case "출금":
            accountNo = operator.prompt(scanner, "계좌번호:");
            account = new Account();
            for(int i = 0; i < accountLength; i++) {
              if(accounts[i].getAccountNo().equals(accountNo)) {
                account = accounts[i];
                break;
              }
            }
            account.setBalance((-1) * Integer.parseInt(operator.prompt(scanner, "출금액:")));
            break;
          case "종료":
            break loop;
          default:
            System.out.println("없는 메뉴입니다.");
        }
      }
    System.out.println("프로그램 종료");
  }

  public String prompt(Scanner scanner, String header) {
    System.out.printf("%s ", header);
    String command = scanner.nextLine();

    return command;
  }

  public void printMenu(String[] menus) {
    String line = "---------------------------------------------------";


    System.out.println(line);
    for(int i = 0; i < menus.length; i++) {
      System.out.printf("%d.%s", (i + 1), menus[i]);
      if(i < menus.length - 1) {
        System.out.print(" | ");
      } else {
        System.out.println();
      }
    }
    System.out.println(line);
  }

  public void printTitle(int menuNo, String[] menus) {
    String line = "------------";

    if(menuNo < menus.length) {
      System.out.println(line);
      System.out.println(menus[menuNo - 1]);
      System.out.println(line);
    }
  }
}
