package book.designpattern.ch10.state;

import java.io.Serializable;

public interface State extends Serializable {
  public void insertQuarter();
  public void ejectQuarter();
  public void turnCrank();
  public void dispense();
  default public void refill() {};
}
