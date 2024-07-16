package book.designpattern.ch07.adapter;

public class DuckTestDrive {
  public static void main(String[] args) {
    Duck duck = new MallardDuck();

    Turkey turkey = new WildTurkey();
    Duck turkeyAdapter = new TurkeyAdapter(turkey);

    System.out.println("칠면조가 말하길");
    turkey.gobble();
    turkey.fly();

    System.out.println("오리가 말하길");
    testDuck(duck);

    System.out.println("칠면조 어댑터가 말하길");
    testDuck(turkeyAdapter);
  }

  public static void testDuck(Duck duck) {
    duck.quack();
    duck.fly();
  }
}
