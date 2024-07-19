package study.patterns.ex02.after1;

public class Printer4 {
  Printer origin = new Printer();
  String footer;

  public Printer4(String footer) {
    this.footer = footer;
  }

  void print(String content) {
    origin.print(content);
    System.out.printf("======== %s =======\n", footer);
  }
}
