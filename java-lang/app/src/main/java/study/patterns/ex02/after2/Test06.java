package study.patterns.ex02.after2;

public class Test06 {
  public static void main(String[] args) {
    Printer printer0 = new ContentPrinter();
    Printer printer1 = new HeaderPrinter(printer0, "편지");
    Printer printer = new SignPrinter(printer1, "홍길동");
    printer.print("안녕하세요.");
  }
}
