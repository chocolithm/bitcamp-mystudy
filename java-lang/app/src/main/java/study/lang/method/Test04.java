package study.lang.method;

public class Test04 {
  public static void main(String[] args) {
    System.out.println(m1("홍길동", 20));
  }

  static String m1(String name, int age) {
    return String.format("%s(%d)님 반가워요", name, age);
  }
}
