package book.designpattern.ch10;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import book.designpattern.ch10.state.HasQuarterState;
import book.designpattern.ch10.state.NoQuarterState;
import book.designpattern.ch10.state.SoldOutState;
import book.designpattern.ch10.state.SoldState;
import book.designpattern.ch10.state.State;
import book.designpattern.ch10.state.WinnerState;

public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {
  private static final long serialVersionUID = 2L;
  State soldOutState;
  State noQuarterState;
  State hasQuarterState;
  State soldState;
  State winnerState;

  State state = soldState;
  int count = 0;
  String location;

  public GumballMachine(String location, int numberGumballs) throws RemoteException {
    soldOutState = new SoldOutState(this);
    noQuarterState = new NoQuarterState(this);
    hasQuarterState = new HasQuarterState(this);
    soldState = new SoldState(this);
    winnerState = new WinnerState(this);

    this.count = numberGumballs;

    if (numberGumballs > 0) {
      state = noQuarterState;
    } else {
      state = soldOutState;
    }
  }

  public void insertQuarter() {
    state.insertQuarter();
  }

  public void ejectQuarter() {
    state.ejectQuarter();
  }

  public void turnCrank() {
    state.turnCrank();
    state.dispense();
  }

  public void releaseBall() {
    System.out.println("알맹이를 내보내고 있습니다.");
    if (count > 0) {
      count--;
    }
  }

  public void refill(int count) {
    this.count += count;
    System.out.println("재입고했습니다.");
    state.refill();
  }

  @Override
  public String toString() {
    String str = "\n주식회사 왕뽑기\n"
        + "자바로 돌아가는 최신형 뽑기 기계\n"
        + "남은 개수: " + count + "개\n";

    if (state == soldState) {
      str += "판매\n";
    } else if (state == noQuarterState) {
      str += "동전 투입 대기 중\n";
    } else if (state == soldOutState) {
      str += "매진\n";
    } else if (state == hasQuarterState) {
      str += "동전 투입됨\n";
    }

    return str;
  }

  public State getSoldOutState() {
    return soldOutState;
  }

  public void setSoldOutState(State soldOutState) {
    this.soldOutState = soldOutState;
  }

  public State getNoQuarterState() {
    return noQuarterState;
  }

  public void setNoQuarterState(State noQuarterState) {
    this.noQuarterState = noQuarterState;
  }

  public State getHasQuarterState() {
    return hasQuarterState;
  }

  public void setHasQuarterState(State hasQuarterState) {
    this.hasQuarterState = hasQuarterState;
  }

  public State getSoldState() {
    return soldState;
  }

  public void setSoldState(State soldState) {
    this.soldState = soldState;
  }

  public State getWinnerState() {
    return winnerState;
  }

  public void setWinnerState(State winnerState) {
    this.winnerState = winnerState;
  }

  @Override
  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  @Override
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }



}
