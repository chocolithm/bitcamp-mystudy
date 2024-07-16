package book.designpattern.ch07.facade;

public class Projector {
  String description;
  StreamingPlayer dvdPlayer;

  public Projector(String description, StreamingPlayer dvdPlayer) {
    this.description = description;
    this.dvdPlayer = dvdPlayer;
  }

  public void on() {
    System.out.println(description + " on");
  }

  public void off() {
    System.out.println(description + " off");
  }

  public void wideScreenMode() {
    System.out.println(description + " in widescreen mode (16x9 aspect ratio)");
  }

  public void tvMode() {
    System.out.println(description + " in tv mode (4x3 aspect ratio)");
  }

  @Override
  public String toString() {
    return description;
  }
}
