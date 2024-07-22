package book.thisisjava.ch13.q04;

public class UtilExample {
  public static void main(String[] args) {
    Pair<String, Integer> pair = new Pair<>("홍길동", 35);
    Integer age = Util.getValue(pair, "홍길동");
    System.out.println(age);

    ChildPair<String, Integer> childPair = new ChildPair<String, Integer>("홍삼원", 20);
    Integer childAge = Util.getValue(childPair, "홍삼순");
    System.out.println(childAge);

    //    OtherPair<String, Integer> otherPair = new OtherPair<String, Integer>("홍삼원", 20);
    //    Integer otherAge = Util.getValue(otherPair, "홍삼순");
    //    System.out.println(otherAge);

  }
}
