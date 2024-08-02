package study.concurrent;

public class Test01 {
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      System.out.printf("==> %d\n", i);
    }

    System.out.println("-----------------------------");

    for (int i = 0; i < 100; i++) {
      System.out.printf("~~~~~~~~~~> %d\n", i);
    }
  }
}
