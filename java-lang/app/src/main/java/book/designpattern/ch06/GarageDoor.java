package book.designpattern.ch06;

public class GarageDoor {
  String place;

  public GarageDoor(String place) {
    this.place = place;
  }

  public void up() {
    System.out.println(place + " door is up");
  }

  public void down() {
    System.out.println(place + " door is down");
  }

  public void stop() {
    System.out.println(place + " door stops");
  }

  public void lightOn() {
    System.out.println(place + " Light is on");
  }

  public void lightOff() {
    System.out.println(place + " Light is off");
  }
}
