package study.patterns.ex02.after2;

public class Test03 {
  public static void main(String[] args) {
    Printer printer0 = new ContentPrinter();
    Printer printer1 = new HeaderPrinter(printer0, "인사말");
    Printer printer = new FooterPrinter(printer1, "비트캠프");
    printer.print("안녕하세요.");
  }
}
