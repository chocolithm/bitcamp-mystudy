package book.designpattern.ch04;

import java.util.Arrays;

public abstract class Pizza {
  String name;
  Dough dough;
  Sauce sauce;
  Veggies veggies[];
  Cheese cheese;
  Pepperoni pepperoni;
  Clams clam;


  abstract void prepare();

  void bake() {
    System.out.println("175도에서 25분간 굽기");
  }

  void cut() {
    System.out.println("피자를 사선으로 자르기");
  }

  void box() {
    System.out.println("상자에 피자 담기");

  }

  void setName(String name) {
    this.name = name;
  }

  String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Pizza [name=" + name + ", dough=" + dough + ", sauce=" + sauce + ", veggies="
        + Arrays.toString(veggies) + ", cheese=" + cheese + ", pepperoni=" + pepperoni + ", clam="
        + clam + "]";
  }


}

