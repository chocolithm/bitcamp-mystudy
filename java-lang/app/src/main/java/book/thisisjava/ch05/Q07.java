package book.thisisjava.ch05;

public class Q07 {
  public static void main(String[] args) {
    int[] array = { 1, 5, 3, 8, 2 };
    int max = Math.max(array[0], array[1]);

    for (int i = 2; i < array.length; i++) {
      max = Math.max(max, array[i]);
    }

    System.out.println(max);
  }
}
