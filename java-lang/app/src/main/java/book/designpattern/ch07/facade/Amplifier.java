package book.designpattern.ch07.facade;

public class Amplifier {
  String description;
  Tuner tuner;
  StreamingPlayer dvd;
  StreamingPlayer cd;

  public Amplifier(String description) {
    this.description = description;
  }

  public void on() {
    System.out.println(description + " on");
  }

  public void off() {
    System.out.println(description + " off");
  }

  public void setStereoSound() {
    System.out.println(description + " stereo mode on");
  }

  public void setSurroundSound() {
    System.out.println(description + " surround sound on (5 speakers, 1 subwoofer)");
  }

  public void setVolume(int level) {
    System.out.println(description + " setting volume to " + level);
  }

  public void setTuner(Tuner tuner) {
    System.out.println(description + " setting tuner to " + dvd);
    this.tuner = tuner;
  }

  public void setDvd(StreamingPlayer dvd) {
    System.out.println(description + " setting DVD player to " + dvd);
    this.dvd = dvd;
  }

  public void setCd(StreamingPlayer cd) {
    System.out.println(description + " setting CD player to " + cd);
    this.cd = cd;
  }

  @Override
  public String toString() {
    return description;
  }
}
