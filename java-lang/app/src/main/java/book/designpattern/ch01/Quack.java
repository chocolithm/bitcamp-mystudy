package book.designpattern.ch01;

public class Quack implements QuackBehavior {
  @Override
  public void quack() {
    System.out.println("꽥");
  }
}
