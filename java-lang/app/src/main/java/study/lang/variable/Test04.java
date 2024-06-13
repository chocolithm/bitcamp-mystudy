package study.lang.variable;

public class Test04 {
  public static void main(String[] args) {
    //    String[][] subMenus = new String[][] {
    //      new String[] {"등록", "목록"},
    //      new String[] {"등록", "목록", "조회"},
    //      new String[] {"등록", "목록", "조회", "변경"},
    //      new String[] {"등록", "목록", "조회", "변경", "삭제"}
    //    };

    //    String[][] subMenus = new String[][] {
    //      {"등록", "목록"},
    //      {"등록", "목록", "조회"},
    //      {"등록", "목록", "조회", "변경"},
    //      {"등록", "목록", "조회", "변경", "삭제"}
    //    };

    String[][] subMenus = {
        {"등록", "목록"},
        {"등록", "목록", "조회"},
        {"등록", "목록", "조회", "변경"},
        {"등록", "목록", "조회", "변경", "삭제"}
    };

    for(int i = 0; i < subMenus.length; i++) {
      for(int j = 0; j < subMenus[i].length; j++) {
        System.out.print(subMenus[i][j] + ",");
      }
      System.out.println();
    }
  }
}
