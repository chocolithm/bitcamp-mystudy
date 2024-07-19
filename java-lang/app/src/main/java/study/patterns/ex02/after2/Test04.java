package study.patterns.ex02.after2;

public class Test04 {
  public static void main(String[] args) {
    Printer printer0 = new ContentPrinter();
    Printer printer = new FooterPrinter(printer0, "비트캠프");
    printer.print("안녕하세요.");
  }
}
