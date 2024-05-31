package study.lang.literal;

// 실습
// - 부동소수점 3.14를 다음의 방식으로 표현하라
//   3.14
//   31.4 * 10^-1
//   314 * 10^-2
//   0.314 * 10^1
//   0.0314 * 10^2
//   exponent 사용
// 
public class Test03 {
    public static void main(String[] args) {
        System.out.println(3.14);
        System.out.println(31.4e-1);
        System.out.println(314e-2);
        System.out.println(0.314e1);
        System.out.println(0.0314e2);
    }
}
