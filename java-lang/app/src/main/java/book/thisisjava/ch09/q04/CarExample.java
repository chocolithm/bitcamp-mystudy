package book.thisisjava.ch09.q04;

public class CarExample {
  public static void main(String[] args) {
    Car myCar = new Car();
    Car.Tire tire = myCar.new Tire();
    Car.Engine engine = new Car.Engine();
  }
}
