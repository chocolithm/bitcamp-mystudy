package study.patterns.ex02.after2;

public class Test07 {
  public static void main(String[] args) {
    Printer printer0 = new ContentPrinter();
    Printer printer1 = new HeaderPrinter(printer0, "편지");
    Printer printer2 = new SignPrinter(printer1, "홍길동");
    Printer printer = new FooterPrinter(printer2, "비트캠프");
    printer.print("안녕하세요.");
  }
}
