package book.designpattern.ch12.factory;

import book.designpattern.ch12.Quackable;

public abstract class AbstractDuckFactory {
  public abstract Quackable createMallardDuck();
  public abstract Quackable createRedheadDuck();
  public abstract Quackable createDuckCall();
  public abstract Quackable createRubberDuck();
  public abstract Quackable createGooseDuck();
}
