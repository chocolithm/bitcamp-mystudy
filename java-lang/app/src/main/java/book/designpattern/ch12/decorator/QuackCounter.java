package book.designpattern.ch12.decorator;

import book.designpattern.ch12.Quackable;
import book.designpattern.ch12.observer.Observer;

public class QuackCounter implements Quackable {
  Quackable duck;
  static int numberOfQuacks;

  public QuackCounter(Quackable duck) {
    this.duck = duck;
  }

  @Override
  public void quack() {
    duck.quack();
    numberOfQuacks++;
  }

  public static int getQuacks() {
    return numberOfQuacks;
  }

  @Override
  public void registerObserver(Observer observer) {
    duck.registerObserver(observer);
  }

  @Override
  public void notifyObserver() {
    duck.notifyObserver();

  }
}
