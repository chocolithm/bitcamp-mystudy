package study.patterns.ex01.step3;

public class Sedan extends Car {
  public boolean sunroof;
  public boolean auto;

  protected Sedan() {}

  @Override
  public String toString() {
    return "Sedan [sunroof=" + sunroof + ", auto=" + auto + ", maker=" + maker + ", model=" + model
        + ", cc=" + cc + "]";
  }
}
