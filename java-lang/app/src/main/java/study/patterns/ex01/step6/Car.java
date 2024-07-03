package study.patterns.ex01.step6;

public abstract class Car {
  public String maker;
  public String model;
  public int cc;

  @Override
  public String toString() {
    return "Car [maker=" + maker + ", model=" + model + ", cc=" + cc + "]";
  }

  public void play() {
    start();
    run();
    stop();
  }

  protected abstract void start();
  protected abstract void run();
  protected abstract void stop();
}
