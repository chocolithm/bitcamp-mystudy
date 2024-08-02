package study.concurrent;

class MyThread extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.printf("스레드 ~~~~> %d\n", i);
    }
  }
}

public class Test02 {
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      System.out.printf("==> %d\n", i);
    }

    System.out.println("-----------------------------");

    Thread thread = new MyThread();
    thread.start();

    for (int i = 0; i < 100; i++) {
      System.out.printf("메인 ~~~~~~~~~~> %d\n", i);
    }
  }
}
