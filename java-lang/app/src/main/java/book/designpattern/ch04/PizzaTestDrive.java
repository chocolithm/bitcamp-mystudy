package book.designpattern.ch04;

public class PizzaTestDrive {
  public static void main(String[] args) {
    PizzaStore nyStore = new NYPizzaStore();
    PizzaStore chicahoPizzaStore = new ChicagoPizzaStore();

    Pizza pizza = nyStore.orderPizza("cheese");
    System.out.println("에단이 주문한" + pizza.getName() + "\n");

    pizza = chicahoPizzaStore.orderPizza("cheese");
    System.out.println("조엘이 주문한" + pizza.getName() + "\n");
  }
}
