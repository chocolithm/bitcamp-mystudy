package thisisjava.ch06;

public class Account {
  private final int MIN_BALANCE = 0;
  private final int MAX_BALANCE = 1000000;
  private int balance;

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    if(balance >= MIN_BALANCE && balance <= MAX_BALANCE) {
      this.balance = balance;
    }
  }
}
