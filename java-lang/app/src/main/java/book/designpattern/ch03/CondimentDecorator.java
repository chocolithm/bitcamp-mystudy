package book.designpattern.ch03;

public abstract class CondimentDecorator extends Beverage {
  Beverage beverage;
  @Override
  public abstract String getDescription();
}
