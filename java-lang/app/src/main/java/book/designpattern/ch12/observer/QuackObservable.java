package book.designpattern.ch12.observer;

public interface QuackObservable {
  public void registerObserver(Observer observer);
  public void notifyObserver();
}
