package study.lang.string;

public class Test03 {
  public static void main(String[] args) {
    java.util.Scanner keyboard = new java.util.Scanner(System.in);

    System.out.println("입력1: ");
    String s1 = keyboard.nextLine(); // 사용자가 입력한 문자열을 저장한 String 인스턴스를 새로 만들고 그 주소를 리턴

    System.out.println("입력2: ");
    String s2 = keyboard.nextLine(); // 사용자가 입력한 문자열을 저장한 String 인스턴스를 새로 만들고 그 주소를 리턴

    System.out.println(s1 == s2); // 레퍼런스에 저장된 주소를 비교, 즉 레퍼런스가 가리키는 인스턴스가 같은지 비교한다.

    System.out.println(s1.equals(s2)); // s1 인스턴스에 저장된 문자열과 s2 인스턴스에 저장된 문자열이 같은지 비교한다.

    keyboard.close();
  }
}
