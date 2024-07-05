package book.designpattern.ch01;

public class Squeak implements QuackBehavior {
  @Override
  public void quack() {
    System.out.println("삑");
  }
}
