package book.thisisjava.ch12;

import java.nio.charset.StandardCharsets;

public class Q09 {
  public static void main(String[] args) throws Exception {
    byte[] bytes = {-20, -107, -120, -21, -123, -107};
    String str = new String(bytes, StandardCharsets.UTF_8);
    System.out.println("str : " + str);
  }
}
