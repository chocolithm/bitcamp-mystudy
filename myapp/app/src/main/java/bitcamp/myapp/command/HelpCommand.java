package bitcamp.myapp.command;

import bitcamp.myapp.util.Stack;

public class HelpCommand implements Command {
  public void execute(Stack menuPath) {
    System.out.println("도움말입니다.");
  }
}
