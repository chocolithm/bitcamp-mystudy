package book.designpattern.ch01;

public class FlyWithWings implements FlyBehavior {
  @Override
  public void fly() {
    System.out.println("날고 있어요!");
  }
}
