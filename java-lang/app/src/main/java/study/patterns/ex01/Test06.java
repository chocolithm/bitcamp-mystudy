// 0) 패턴 적용 전
// 1) private 생성자 + Factory Method 설계 패턴
// 2) Abstract Factory 설계 패턴
// 3) Abstract Factory 설계 패턴 + 인터페이스
// 5) Singleton 설계 패턴

package study.patterns.ex01;

import study.patterns.ex01.step6.Car;
import study.patterns.ex01.step6.CarFactory;
import study.patterns.ex01.step6.K7Factory;
import study.patterns.ex01.step6.SonataFactory;

public class Test06 {
  public static void main(String[] args) {
    SonataFactory sonataFactory = SonataFactory.getInstance();
    K7Factory k7Factory = K7Factory.getInstance();

    play(sonataFactory);
    play(k7Factory);
  }

  static void play(CarFactory carFactory) {
    Car car = carFactory.createCar();
    car.play();
  }
}
