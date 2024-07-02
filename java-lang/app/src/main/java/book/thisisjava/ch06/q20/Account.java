package book.thisisjava.ch06.q20;

public class Account {
  private String accountNo;
  private String owner;
  private int balance;

  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    if (this.balance + balance < 0) {
      System.out.println("잔액이 부족합니다.");
    } else {
      if (balance < 0) {
        System.out.println("결과: 출금이 성공되었습니다.");
      }
      this.balance += balance;
    }
  }

}
