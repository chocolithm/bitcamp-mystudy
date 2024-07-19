package book.thisisjava.ch12;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Q16 {
  public static void main(String[] args) {
    System.out.println(new SimpleDateFormat("yyyy년 MM월 dd일 E요일 hh시 mm분").format(new Date()));
  }
}
