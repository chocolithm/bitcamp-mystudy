package study.lang.operator;

// 연산의 결과는 int로 반환되기 때문에, 아래 예시에서는 명시적 형변환이 필요
// - byte, char, short --> int --> long --> float --> double

public class Test04 {
  public static void main(String[] args) {
    System.out.println(3 + 5 * 2);
    System.out.println(5 * 2 + 3);
    System.out.println((3 + 5) * 2);

    // 암시적 형변환 + 연산자 우선순위
    System.out.println(3.2f + 5 / 2L);

    // 명시적 형변환 = 연산자 우선순위
    System.out.println(3.2f + (float) 5 / 2L);
  }
}
