// 실습
// - 다음과 같이 실행결과가 나오도록 코드를 완성하라.
//      $ java -classpath ... Test6 aaa bbb ccc ddd
//      'aaa'
//      'bbb'
//      'ccc'
//      'ddd'

class Test6 {
    public static void main(String[] args) {
        // System.out.println("'" + args[0] + "'");
        // System.out.println("'" + args[1] + "'");
        // System.out.println("'" + args[2] + "'");
        // System.out.println("'" + args[3] + "'");

        // for (int i = 0; i < args.length; i++) {
        // System.out.println("'" + args[i] + "'");
        // }

        for (String data : args) {
            System.out.println("'" + data + "'");
        }
    }
}
