package book.designpattern.ch12.duck;

import book.designpattern.ch12.Quackable;
import book.designpattern.ch12.observer.Observable;
import book.designpattern.ch12.observer.Observer;

public class RubberDuck implements Quackable {
  Observable observable;

  public RubberDuck() {
    observable = new Observable(this);
  }

  @Override
  public void quack() {
    System.out.println("삑삑");
    notifyObserver();
  }

  @Override
  public void registerObserver(Observer observer) {
    observable.registerObserver(observer);
  }

  @Override
  public void notifyObserver() {
    observable.notifyObserver();

  }
}