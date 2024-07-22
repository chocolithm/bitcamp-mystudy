package book.thisisjava.ch14.q02;

public class MovieThread extends Thread {
  @Override
  public void run() {
    for(int i = 0; i < 3; i++) {
      System.out.println("동영상 재생");
      try {
        Thread.sleep(1000);
      } catch(InterruptedException e) {

      }
    }
  }
}
