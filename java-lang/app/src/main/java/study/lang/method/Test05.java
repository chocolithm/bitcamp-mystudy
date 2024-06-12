package study.lang.method;

public class Test05 {
  public static void main(String[] args) {
    int result = m1(0);
    System.out.println(result);
  }

  static int m1(int value) {
    value += 1;
    return m2(value);
  }

  static int m2(int value) {
    value += 10;
    return m3(value);
  }

  static int m3(int value) {
    value += 100;
    return m4(value);
  }

  static int m4(int value) {
    return value + 1000;
  }
}
