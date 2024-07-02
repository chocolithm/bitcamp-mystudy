package book.thisisjava.ch06;

public class ShopService {
  private static ShopService singleton = new ShopService();

  private ShopService() {
  }

  public static ShopService getInstance() {
    return singleton;
  }
}
