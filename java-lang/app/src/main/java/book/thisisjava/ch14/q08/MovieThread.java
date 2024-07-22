package book.thisisjava.ch14.q08;

public class MovieThread extends Thread {
  @Override
  public void run() {
    while(true) {
      System.out.println("동영상 재생");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {

      }
    }
  }
}
