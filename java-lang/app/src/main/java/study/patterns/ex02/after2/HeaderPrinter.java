package study.patterns.ex02.after2;

public class HeaderPrinter extends PrinterDecorator {
  String header;

  public HeaderPrinter(Printer origin, String header) {
    super(origin);
    this.header = header;
  }

  @Override
  public void print(String content) {
    System.out.printf("[ %s ]-----------\n", header);
    origin.print(content);
  }
}
