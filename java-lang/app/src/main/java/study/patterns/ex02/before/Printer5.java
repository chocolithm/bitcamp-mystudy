package study.patterns.ex02.before;

public class Printer5 extends Printer {
  String sign;

  public Printer5(String sign) {
    this.sign = sign;
  }

  @Override
  void print(String content) {
    super.print(content);
    System.out.println();
    System.out.printf("from %s\n", sign);
    System.out.println();
  }
}
