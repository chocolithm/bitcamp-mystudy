package study.lang.operator;

// 실습
// - 산술 연산자를 사용하여 연산을 수행한 후 결과를 확인하라.

// 학습 내용
// 정수와 정수의 연산 결과는 정수이다.
// 부동소수점과 부동소수점의 연산 결과는 부동소수점이다.
// 정수와 부동소수점의 연산 결과는 부동소수점이다.
// => 연산은 반드시 같은 타입만 가능
// => 타입이 다르면 타입을 맞춘 후 연산을 수행
// => 타입을 맞추는 규칙
// 정수 --> 부동소수점
// byte, short, char, int, long --> float, double

public class Test01 {
  public static void main(String[] args) {
    System.out.println(100 + 24);
    System.out.println(100 - 24);
    System.out.println(100 * 24);
    System.out.println(100 / 24);
    System.out.println(100 % 24);

    // 부동소수점의 연산 (소수점 최소 한 자리)
    System.out.println(124.7 + 25.0);
    System.out.println(124.7 - 25.0);
    System.out.println(124.7 * 25.0);
    System.out.println(124.7 / 25.0);
    System.out.println(124.7 % 25.0);

    // 정수와 부동소수점의 연산 결과
    System.out.println(5 / 2.0);
    System.out.println(5.0 / 2);
  }
}
