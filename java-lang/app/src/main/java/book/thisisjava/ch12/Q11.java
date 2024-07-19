package book.thisisjava.ch12;

import java.util.StringTokenizer;

public class Q11 {
  public static void main(String[] args) {
    StringTokenizer str = new StringTokenizer("아이디,이름,패스워드", ",");
    while(str.hasMoreTokens()) {
      System.out.println(str.nextToken());
    }
  }
}
