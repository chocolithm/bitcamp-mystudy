package book.thisisjava.ch14.q06;

public class MovieThread extends Thread {
  @Override
  public void run() {
    while(true) {
      System.out.println("동영상 재생");
      if(this.isInterrupted()) {
        break;
      }
    }
  }
}
