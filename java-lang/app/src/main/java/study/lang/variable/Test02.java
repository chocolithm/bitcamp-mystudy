package study.lang.variable;

// 문자코드 저장방법 확인

public class Test02 {
  public static void main(String[] args) {
    char c1 = 44032; // 변수에 '가' 문자의 코드를 정수값으로 저장
    char c2 = 0xAC00;
    char c3 = '\uAC00'; // 변수에 '가' 문자의 코드를 \u0000 형태의 유니코드로 표현법으로 저장
    char c4 = '가'; // 변수에 '가' 문자의 코드를 가장 쉬운 방법으로 저장하라

    System.out.println(c1);
    System.out.println(c2);
    System.out.println(c3);
    System.out.println(c4);
  }
}
