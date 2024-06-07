package study.lang.operator;

// 연산의 결과는 int로 반환되기 때문에, 아래 예시에서는 명시적 형변환이 필요
// - byte, char, short --> int --> long --> float --> double

public class Test03 {
  public static void main(String[] args) {
    int r = 3 + 5 * 2;
    int r2 = 5 * 2 + 3;
  }
}
