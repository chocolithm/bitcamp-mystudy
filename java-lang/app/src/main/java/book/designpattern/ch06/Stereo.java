package book.designpattern.ch06;

public class Stereo {
  String place;

  public Stereo(String place) {
    this.place = place;
  }


  public void on() {
    System.out.println(place + " stereo is on");
  }

  public void off() {
    System.out.println(place + " stereo is off");
  }

  public void setCD() {
    System.out.println("CD is set");
  }

  public void setDVD() {
    System.out.println("DVD is set");
  }

  public void setRadio() {
    System.out.println("Radio is set");
  }

  public void setVolume(int volume) {
    System.out.println("Volue is " + volume);
  }
}
