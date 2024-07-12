package study.oop.nested;

public class Test01 {
  interface Printer {
    void print();
  }

  public static void main(String[] args) {

    Printer obj;
    obj = () -> {
      System.out.println("Hello!");
    };

    obj.print();

  }
}
