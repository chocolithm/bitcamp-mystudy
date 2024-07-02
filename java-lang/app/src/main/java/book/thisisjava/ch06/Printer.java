package book.thisisjava.ch06;

public class Printer {
  public static void main(String[] args) {
    Printer.println(10);
    Printer.println(true);
    Printer.println(5.7);
    Printer.println("홍길동");
  }

  public static void println(int text) {
    System.out.println(text);
  }

  public static void println(boolean text) {
    System.out.println(text);
  }

  public static void println(double text) {
    System.out.println(text);
  }

  public static void println(String text) {
    System.out.println(text);
  }
}
