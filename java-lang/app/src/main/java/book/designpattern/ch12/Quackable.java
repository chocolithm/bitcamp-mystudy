package book.designpattern.ch12;

import book.designpattern.ch12.observer.QuackObservable;

public interface Quackable extends QuackObservable {
  public void quack();
}
