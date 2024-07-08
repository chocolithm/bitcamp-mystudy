package book.designpattern.ch06;

public class StereoOnWithRadioCommand implements Command {
  Stereo stereo;

  public StereoOnWithRadioCommand(Stereo stereo) {
    this.stereo = stereo;
  }

  @Override
  public void execute() {
    stereo.on();
    stereo.setRadio();
    stereo.setVolume(11);
  }

  @Override
  public void undo() {
    stereo.off();
  }
}
