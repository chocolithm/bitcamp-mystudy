package thisisjava.ch04;

public class Q04 {
  public static void main(String[] args) {
    int a = (int) (Math.random() * 6) + 1;
    int b = (int) (Math.random() * 6) + 1;

    while (a + b != 5) {
      a = (int) (Math.random() * 6) + 1;
      b = (int) (Math.random() * 6) + 1;
      System.out.printf("(%d, %d)\n", a, b);
    }
  }
}
