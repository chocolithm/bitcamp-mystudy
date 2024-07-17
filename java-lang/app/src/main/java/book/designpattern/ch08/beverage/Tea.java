package book.designpattern.ch08.beverage;

public class Tea extends CaffeineBeverage {

  @Override
  public void brew() {
    System.out.println("찻잎 우리는 중");
  }

  @Override
  public void addCondiments() {
    System.out.println("레몬 추가하는 중");
  }
}
