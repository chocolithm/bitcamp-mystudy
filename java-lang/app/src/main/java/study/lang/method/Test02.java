package study.lang.method;

public class Test02 {
  public static void main(String[] args) {
    m1("Hong", 20);
    m1("Lim", 30);
    m1("Yoo", 10);
  }

  static void m1(String name, int age) {
    System.out.printf("%s(%d)님 반갑습니다.\n", name, age);
  }
}
