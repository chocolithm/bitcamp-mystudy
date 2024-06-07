package study.lang.operator;

// 연산의 결과는 int로 반환되기 때문에, 아래 예시에서는 명시적 형변환이 필요

public class Test02 {
  public static void main(String[] args) {
    byte b1 = 100;
    byte b2 = 20;
    byte b3 = (byte) (b1 + b2);

    int r = b1 + b2;

    char c = 20;
    short s = 30;


    long i1 = 22_0000_0000L;
    long i2 = 21_0000_0000;
    long i3 = i1 + i2;

    long r3 = i1 + i2;

    System.out.println(r3);
  }
}
