package study.patterns.ex02.after2;

public class FooterPrinter extends PrinterDecorator {
  String footer;

  public FooterPrinter(Printer printer, String footer) {
    super(printer);
    this.footer = footer;
  }

  @Override
  public void print(String content) {
    origin.print(content);
    System.out.printf("======== %s =======\n", footer);
  }
}
