package book.thisisjava.ch12;

public class Q10 {
  public static void main(String[] args) {
    StringBuilder str = new StringBuilder();
    for(int i = 1; i <= 100; i++) {
      str.append(i);
    }
    System.out.println(str);
  }
}
