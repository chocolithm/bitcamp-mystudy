package book.designpattern.ch12.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import book.designpattern.ch12.Quackable;
import book.designpattern.ch12.observer.Observer;

public class Flock implements Quackable {
  List<Quackable> quackers = new ArrayList<>();

  public void add(Quackable quacker) {
    quackers.add(quacker);
  }

  @Override
  public void quack() {
    Iterator<Quackable> iterator = quackers.iterator();
    while (iterator.hasNext()) {
      Quackable duck = iterator.next();
      duck.quack();
    }
  }

  @Override
  public void registerObserver(Observer observer) {
    Iterator<Quackable> iterator = quackers.iterator();
    while (iterator.hasNext()) {
      Quackable duck = iterator.next();
      duck.registerObserver(observer);
    }
  }

  @Override
  public void notifyObserver() {

  }
}
