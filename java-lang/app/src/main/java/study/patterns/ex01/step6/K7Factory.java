package study.patterns.ex01.step6;

public class K7Factory implements CarFactory {

  private K7Factory() {

  }

  private static K7Factory instance;

  public static K7Factory getInstance() {
    if(instance == null) {
      instance = new K7Factory();
    }
    return instance;
  }

  @Override
  public Car createCar() {
    Sedan s = new Sedan();

    s.maker = "기아자동차";
    s.model = "K7";
    s.cc = 2500;
    s.auto = true;
    s.sunroof = true;

    return s;
  }
}
