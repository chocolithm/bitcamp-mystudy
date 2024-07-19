package study.patterns.ex02.after2;

public class Test02 {
  public static void main(String[] args) {
    Printer printer0 = new ContentPrinter();
    Printer printer = new HeaderPrinter(printer0, "공지사항");
    printer.print("안녕하세요.");
  }
}
