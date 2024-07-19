package book.thisisjava.ch12;

public class Q08 {
  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    int[] scores = new int[1000];
    for(int i = 0; i < scores.length; i++) {
      scores[i] = i;
    }

    int sum = 0;
    for(int score : scores) {
      sum += score;
    }

    double avg = sum / scores.length;
    System.out.println(avg);
    System.out.println(System.currentTimeMillis() - startTime);
  }
}
