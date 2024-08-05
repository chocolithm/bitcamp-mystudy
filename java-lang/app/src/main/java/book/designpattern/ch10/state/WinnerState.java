package book.designpattern.ch10.state;

import book.designpattern.ch10.GumballMachine;

public class WinnerState implements State {
  private static final long serialVersionUID = 2L;
  transient GumballMachine gumballMachine;

  public WinnerState(GumballMachine gumballMachine) {
    this.gumballMachine = gumballMachine;
  }

  @Override
  public void insertQuarter() {
    System.out.println("알맹이를 내보내고 있습니다.");
  }

  @Override
  public void ejectQuarter() {
    System.out.println("이미 알맹이를 뽑으셨습니다.");
  }

  @Override
  public void turnCrank() {
    System.out.println("손잡이는 한 번만 돌려주세요.");
  }

  @Override
  public void dispense() {
    gumballMachine.releaseBall();

    if (gumballMachine.getCount() == 0) {
      gumballMachine.setState(gumballMachine.getSoldOutState());
    } else {
      gumballMachine.releaseBall();
      System.out.println("축하드립니다. 알맹이를 하나 더 받으실 수 있습니다.");
      if (gumballMachine.getCount() > 0) {
        gumballMachine.setState(gumballMachine.getNoQuarterState());
      } else {
        System.out.println("매진입니다.");
        gumballMachine.setState(gumballMachine.getSoldOutState());
      }
    }
  }

  @Override
  public String toString() {
    return "WinnerState [gumballMachine=" + gumballMachine + "]";
  }

}