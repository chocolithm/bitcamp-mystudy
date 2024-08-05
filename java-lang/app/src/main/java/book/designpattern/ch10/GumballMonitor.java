package book.designpattern.ch10;

public class GumballMonitor {
  GumballMachine machine;

  public GumballMonitor(GumballMachine machine) {
    this.machine = machine;
  }

  public void report() {
    System.out.println("위치 : " + machine.getLocation());
    System.out.println("재고 : " + machine.getCount() + "개");
    System.out.println("상태 : " + machine.getState());
  }
}
