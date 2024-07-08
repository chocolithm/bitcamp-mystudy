package book.designpattern.ch06;

public class StereoOnWithDVDCommand implements Command {
  Stereo stereo;

  public StereoOnWithDVDCommand(Stereo stereo) {
    this.stereo = stereo;
  }

  @Override
  public void execute() {
    stereo.on();
    stereo.setDVD();
    stereo.setVolume(11);
  }

  @Override
  public void undo() {
    stereo.off();
  }
}
