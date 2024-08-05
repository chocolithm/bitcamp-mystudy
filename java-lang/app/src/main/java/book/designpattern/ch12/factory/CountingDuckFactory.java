package book.designpattern.ch12.factory;

import book.designpattern.ch12.Quackable;
import book.designpattern.ch12.adapter.GooseAdapter;
import book.designpattern.ch12.decorator.QuackCounter;
import book.designpattern.ch12.duck.DuckCall;
import book.designpattern.ch12.duck.Goose;
import book.designpattern.ch12.duck.MallardDuck;
import book.designpattern.ch12.duck.RedheadDuck;
import book.designpattern.ch12.duck.RubberDuck;

public class CountingDuckFactory extends AbstractDuckFactory {

  @Override
  public Quackable createMallardDuck() {
    return new QuackCounter(new MallardDuck());
  }

  @Override
  public Quackable createRedheadDuck() {
    return new QuackCounter(new RedheadDuck());
  }

  @Override
  public Quackable createDuckCall() {
    return new QuackCounter(new DuckCall());
  }

  @Override
  public Quackable createRubberDuck() {
    return new QuackCounter(new RubberDuck());
  }

  @Override
  public Quackable createGooseDuck() {
    return new GooseAdapter(new Goose());
  }

}
