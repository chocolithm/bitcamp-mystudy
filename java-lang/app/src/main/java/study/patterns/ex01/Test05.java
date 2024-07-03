// 0) 패턴 적용 전
// 1) private 생성자 + Factory Method 설계 패턴
// 2) Abstract Factory 설계 패턴
// 3) Abstract Factory 설계 패턴 + 인터페이스
// 5) Singleton 설계 패턴

package study.patterns.ex01;

import study.patterns.ex01.step5.Car;
import study.patterns.ex01.step5.CarFactory;
import study.patterns.ex01.step5.K7Factory;
import study.patterns.ex01.step5.SonataFactory;

public class Test05 {
  public static void main(String[] args) {
    SonataFactory sonataFactory = SonataFactory.getInstance();
    K7Factory k7Factory = K7Factory.getInstance();

    printCar(sonataFactory);
    printCar(k7Factory);
  }

  static void printCar(CarFactory carFactory) {
    Car car = carFactory.createCar();
    System.out.println(car);
  }
}
