package bitcamp.myapp.command;

import bitcamp.command.Command;

public class HelpCommand implements Command {

  public void execute(String menuName) {
    System.out.println("도움말입니다!");
  }
}
