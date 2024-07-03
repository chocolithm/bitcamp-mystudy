// 0) 패턴 적용 전
// 1) private 생성자 + Factory Method 설계 패턴
// 2) Abstract Factory 설계 패턴
// 3) Abstract Factory 설계 패턴 + 인터페이스

package study.patterns.ex01;

import study.patterns.ex01.step4.Car;
import study.patterns.ex01.step4.CarFactory;
import study.patterns.ex01.step4.K7Factory;
import study.patterns.ex01.step4.SonataFactory;

public class Test04 {
  public static void main(String[] args) {
    SonataFactory sonataFactory = new SonataFactory();
    SonataFactory sonataFactory2 = new SonataFactory();
    SonataFactory sonataFactory3 = new SonataFactory();
    K7Factory k7Factory = new K7Factory();

    printCar(sonataFactory);
    printCar(k7Factory);
  }

  static void printCar(CarFactory carFactory) {
    Car car = carFactory.createCar();
    System.out.println(car);
  }
}
