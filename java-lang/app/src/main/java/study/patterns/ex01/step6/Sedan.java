package study.patterns.ex01.step6;

public class Sedan extends Car {
  public boolean sunroof;
  public boolean auto;

  protected Sedan() {}

  @Override
  public String toString() {
    return "Sedan [sunroof=" + sunroof + ", auto=" + auto + ", maker=" + maker + ", model=" + model
        + ", cc=" + cc + "]";
  }

  @Override
  protected void start() {
    System.out.printf("%s 시동건다!!\n", this.model);
  }

  @Override
  protected void run() {
    System.out.printf("%s %s 달린다!!\n", this.model, this.sunroof ? "선루프 열고" : "선루프 닫고");
  }

  @Override
  protected void stop() {
    System.out.printf("%s 시동끈다!!\n", this.model);
  }
}
