package book.designpattern.ch10.state;

import book.designpattern.ch10.GumballMachine;

public class SoldOutState implements State {
  private static final long serialVersionUID = 2L;
  transient GumballMachine gumballMachine;

  public SoldOutState(GumballMachine gumballMachine) {
    this.gumballMachine = gumballMachine;
  }

  @Override
  public void insertQuarter() {
    System.out.println("죄송합니다. 매진되었습니다.");
  }

  @Override
  public void ejectQuarter() {
    System.out.println("동전을 반환할 수 없습니다. 동전을 넣지 않았습니다.");
  }

  @Override
  public void turnCrank() {
    System.out.println("죄송합니다. 매진되었습니다.");
  }

  @Override
  public void dispense() {
    System.out.println("알맹이를 내보낼 수 없습니다.");
  }

  @Override
  public void refill() {
    gumballMachine.setState(gumballMachine.getNoQuarterState());
  }

  @Override
  public String toString() {
    return "SoldOutState [gumballMachine=" + gumballMachine + "]";
  }



}