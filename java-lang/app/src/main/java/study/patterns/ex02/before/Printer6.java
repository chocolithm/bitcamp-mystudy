package study.patterns.ex02.before;

public class Printer6 extends Printer5 {
  String header;

  public Printer6(String sign, String header) {
    super(sign);
    this.header = header;
  }

  @Override
  void print(String content) {
    super.print(content);
    System.out.println();
    System.out.printf("from %s\n", sign);
    System.out.println();
    System.out.printf("======== %s =======\n", footer);
  }
}
