package study.lang.method;

public class Test03 {
  public static void main(String[] args) {
    m1(); // 반환된 값을 받지 않는 것도 가능
    String message = m1();
    System.out.printf("%s\n", message);
  }

  static String m1() {
    return "홍길동님 안녕!!";
  }
}
