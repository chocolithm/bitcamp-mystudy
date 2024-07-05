package book.designpattern.ch01;

public class MiniDuckSimulator {
  public static void main(String[] args) {
    Duck mallard = new MallardDuck();

    mallard.performQuack();
    mallard.performFly();

    Duck modelDuck = new ModelDuck();

    modelDuck.performFly();
    modelDuck.setFlyBehavior(new FlyRocketPowered());
    modelDuck.performFly();
  }
}
