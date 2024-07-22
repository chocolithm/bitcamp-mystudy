package book.thisisjava.ch14.q06;

public class ThreadExample {
  public static void main(String[] args) {
    Thread thread = new MovieThread();
    thread.start();

    try {
      Thread.sleep(3000);
    } catch(InterruptedException e) {

    }

    thread.interrupt();
  }
}
