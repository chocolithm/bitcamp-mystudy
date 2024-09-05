package study.oop;

public class A {
  public void m1() {
    this.m2();
  }

  public void m2() {
    System.out.println("A.m2()");
  }
}
