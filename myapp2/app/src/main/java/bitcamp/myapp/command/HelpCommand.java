package bitcamp.myapp.command;

public class HelpCommand implements Command {

  public void execute(String menuName) {
    System.out.println("도움말입니다!");
  }
}
