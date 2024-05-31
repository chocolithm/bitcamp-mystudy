package study.lang.literal;

// 실습
// - 문자 A를 유니코드로 표현하라.
//   
// 
public class Test04 {
    public static void main(String[] args) {
        System.out.println('A'); // 문자 리터럴
        System.out.println('\u0041'); // 유니코드 문자 리터럴
        System.out.println('a'); //
        System.out.println('\u0061'); // 유니코드 문자 리터럴
        System.out.println('1');
        System.out.println('\u0031'); // 유니코드 문자 리터럴
        System.out.println('가');
        System.out.println('\uAC00'); // 유니코드 문자 리터럴
        System.out.println('똥');
        System.out.println('\uB625'); // 유니코드 문자 리터럴
        System.out.println('\u00A9'); // 카피라이트 문자 리터럴
    }
}