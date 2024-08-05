package book.designpattern.ch12.adapter;

import book.designpattern.ch12.Quackable;
import book.designpattern.ch12.duck.Goose;
import book.designpattern.ch12.observer.Observable;
import book.designpattern.ch12.observer.Observer;

public class GooseAdapter implements Quackable {
  Goose goose;
  Observable observable;

  public GooseAdapter(Goose goose) {
    this.goose = goose;
    observable = new Observable(this);
  }

  @Override
  public void quack() {
    goose.honk();
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
