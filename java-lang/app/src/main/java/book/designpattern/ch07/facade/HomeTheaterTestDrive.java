package book.designpattern.ch07.facade;

public class HomeTheaterTestDrive {
  public static void main(String[] args) {
    Amplifier amp = new Amplifier("앰프");
    Tuner tuner = new Tuner("튜너", amp);
    StreamingPlayer player = new StreamingPlayer("플레이어", amp);
    Projector projector = new Projector("프로젝터", player);
    TheaterLights lights = new TheaterLights("빛");
    Screen screen = new Screen("스크린");
    PopcornPopper popper = new PopcornPopper("팝콘");

    HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, tuner, player, projector, lights, screen, popper);

    homeTheater.watchMovie("인디아나존스:레이더스");
    homeTheater.endMovie();
  }
}
