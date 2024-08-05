package book.designpattern.ch12;

import book.designpattern.ch12.adapter.GooseAdapter;
import book.designpattern.ch12.composite.Flock;
import book.designpattern.ch12.decorator.QuackCounter;
import book.designpattern.ch12.duck.Goose;
import book.designpattern.ch12.factory.AbstractDuckFactory;
import book.designpattern.ch12.factory.CountingDuckFactory;
import book.designpattern.ch12.observer.Quackologist;

public class DuckSimulator {
  public static void main(String[] args) {
    DuckSimulator simulator = new DuckSimulator();
    AbstractDuckFactory duckFactory = new CountingDuckFactory();

    simulator.simulate(duckFactory);
  }

  private void simulate(AbstractDuckFactory duckFactory) {
    Quackable mallardDuck = duckFactory.createMallardDuck();
    Quackable redheadDuck =duckFactory.createRedheadDuck();
    Quackable duckCall = duckFactory.createDuckCall();
    Quackable rubberDuck = duckFactory.createRubberDuck();
    Quackable gooseDuck =new GooseAdapter(new Goose());

    System.out.println("\n오리 시뮬레이션 게임");

    Flock flockOfDucks = new Flock();

    flockOfDucks.add(redheadDuck);
    flockOfDucks.add(duckCall);
    flockOfDucks.add(rubberDuck);
    flockOfDucks.add(gooseDuck);

    Flock flockOfMallards = new Flock();

    Quackable mallardOne = duckFactory.createMallardDuck();
    Quackable mallardTwo= duckFactory.createMallardDuck();
    Quackable mallardThree = duckFactory.createMallardDuck();
    Quackable mallardFour = duckFactory.createMallardDuck();

    flockOfMallards.add(mallardOne);
    flockOfMallards.add(mallardTwo);
    flockOfMallards.add(mallardThree);
    flockOfMallards.add(mallardFour);

    flockOfDucks.add(flockOfMallards);

    Quackologist quackologist = new Quackologist();
    flockOfDucks.registerObserver(quackologist);

    System.out.println("\n전체 무리");
    simulate(flockOfDucks);

    System.out.println("\n물오리");
    simulate(flockOfMallards);

    System.out.println("오리가 소리 낸 횟수: " + QuackCounter.getQuacks() + "번");
  }

  private void simulate(Quackable duck) {
    duck.quack();
  }
}
