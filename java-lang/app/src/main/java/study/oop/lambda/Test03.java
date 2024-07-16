package study.oop.lambda;

public class Test03 {

  interface Calculator {
    int plus(int a, int b);
  }

  static void test(Calculator c) {
    System.out.println(c.plus(100, 200));
  }

  public static void main(String[] args) {
    // 1) 일반 클래스
    class MyCalc implements Calculator {
      @Override
      public int plus(int a, int b) {
        return a + b;
      }
    }

    test(new MyCalc());

    // 2) 익명 클래스
    Calculator c2 = new Calculator() {
      @Override
      public int plus(int a, int b) {
        return a + b;
      }
    };
    test(c2);

    // 3) 익명 클래스 직접 대입
    test(new Calculator() {
      @Override
      public int plus(int a, int b) {
        return a + b;
      }
    });


    // 4) 람다
    Calculator c4 = (int a, int b) -> {
      return a + b;
    };
    test(c4);

    // 5) 람다 + 중괄호 생략
    Calculator c5 = (a, b) -> a + b;
    test(c5);

    // 6) 람다 직접 대입
    test((a, b) -> a + b);
  }

}
