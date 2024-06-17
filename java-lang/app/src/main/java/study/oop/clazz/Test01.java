package study.oop.clazz;

// 연습: 클래스 문법을 메서드 분류하는 용도로 사용
// 1) 메서드 분류
// 2) static 필드 사용
// 3) non-static 필드 사용
// 4) 인스턴스 메서드 사용
// 5) private, getter 사용
// 6) 인스턴스 메서드 사용: clear()

public class Test01 {
  public static void main(String[] args) {
    // 다음 식을 연산자 우선 순위를 고려하지 않고 순서대로 계산하라!
    // 2 + 3 - 1 * 7 / 3 = ?

    // 계산 결과를 담을 변수를 준비한다.
    Calculator c1 = new Calculator();

    // 메서드를 호출하여 작업을 수행하고,
    // 리턴 결과는 로컬 변수에 저장한다.
    c1.plus(2);
    c1.plus(3);
    c1.minus(1);
    c1.multiple(7);
    c1.divide(3);

    System.out.printf("result = %d\n", c1.getResult());

    c1.clear();
    c1.plus(2);
    c1.multiple(7);
    c1.divide(3);

    System.out.printf("result2 = %d\n", c1.getResult());
  }
}
