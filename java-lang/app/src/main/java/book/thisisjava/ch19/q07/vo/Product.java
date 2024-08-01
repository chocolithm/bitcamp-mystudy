package book.thisisjava.ch19.q07.vo;

public class Product {
  private int no;
  private String name;
  private double price;
  private int stock;

  public Product() {

  }

  public Product(int no, String name, double price, int stock) {
    super();
    this.no = no;
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }


}
