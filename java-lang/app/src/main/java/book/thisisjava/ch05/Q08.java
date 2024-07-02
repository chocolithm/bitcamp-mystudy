package book.thisisjava.ch05;

public class Q08 {
  public static void main(String[] args) {
    int[][] array = {
        { 95, 86 },
        { 83, 92, 96 },
        { 78, 83, 93, 87, 88 }
    };
    int sum;
    double avg;

    for (int i = 0; i < array.length; i++) {
      sum = 0;
      avg = 0;
      for (int j = 0; j < array[i].length; j++) {
        sum += array[i][j];
        if (j == array[i].length - 1) {
          avg = (double) sum / (double) array[i].length;
        }
      }
      System.out.printf("%d반 합: %s\n", (i + 1), sum);
      System.out.printf("%d반 평균: %s\n", (i + 1), avg);
    }
  }
}
