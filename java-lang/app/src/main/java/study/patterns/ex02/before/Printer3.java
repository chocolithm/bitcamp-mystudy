package study.patterns.ex02.before;

public class Printer3 extends Printer2 {
  String footer;

  public Printer3(String header, String footer) {
    super(header);
    this.footer = footer;
  }

  @Override
  void print(String content) {
    super.print(content);
    System.out.printf("======== %s =======\n", footer);
  }
}
