package study.lang.variable;

public class Test01 {
  public static void main(String[] args) {
    byte b1 = -128;
    byte b2 = 127;

    short s1 = -32768;
    short s2 = 32767;

    int i1 = -2147483648;
    int i2 = 2147483647;

    long l1 = -9223372036854775808L;
    long l2 = 9223372036854775807L;

    float f1 = 1.4e-45f;
    float f2 = 3.4028235e38f;

    double d1 = 4.9e-324;
    double d2 = 1.7976931348623157e308;

    char c1 = '\u0000';
    char c2 = '\uFFFF';
    // char c1 = 0;
    // char c2 = 65535;

    boolean bool1 = true;
    boolean bool2 = false;

    System.out.println(b1);
    System.out.println(b2);
    System.out.println(s1);
    System.out.println(s2);
    System.out.println(i1);
    System.out.println(i2);
    System.out.println(l1);
    System.out.println(l2);
    System.out.println(f1);
    System.out.println(f2);
    System.out.println(d1);
    System.out.println(d2);
    System.out.println(c1);
    System.out.println(c2);
    System.out.println(bool1);
    System.out.println(bool2);
  }
}
