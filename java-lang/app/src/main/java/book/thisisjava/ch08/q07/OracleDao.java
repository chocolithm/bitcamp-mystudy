package book.thisisjava.ch08.q07;

public class OracleDao implements DataAccessObject {
  String db = "Oracle";

  @Override
  public void select() {
    System.out.printf("%s DB에서 검색\n", db);
  }

  @Override
  public void insert() {
    System.out.printf("%s DB에 삽입\n", db);
  }

  @Override
  public void update() {
    System.out.printf("%s DB를 수정\n", db);
  }

  @Override
  public void delete() {
    System.out.printf("%s DB에서 삭제\n", db);
  }

}
