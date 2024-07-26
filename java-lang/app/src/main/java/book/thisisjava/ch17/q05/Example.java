package book.thisisjava.ch17.q05;

import java.util.Arrays;
import java.util.List;

public class Example {
  public static void main(String[] args) {
    List<String> list = Arrays.asList(
        "This is a java book",
        "Lambda Expressions",
        "Java8 supports"
        );

    list.stream()
    .forEach(s -> {
      if(s.toLowerCase().contains("java")) {
        System.out.println(s);
      }
    });
  }
}
