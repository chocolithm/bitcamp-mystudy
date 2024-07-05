package book.designpattern.ch01;

public class FlyNoWay implements FlyBehavior {
  @Override
  public void fly() {
    System.out.println("저는 못 날아요..");
  }
}
