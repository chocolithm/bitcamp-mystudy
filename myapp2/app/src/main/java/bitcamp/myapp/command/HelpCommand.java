package bitcamp.myapp.command;

import java.util.Stack;

public class HelpCommand implements Command {

  public void execute(Stack<String> menuPath) {
    System.out.println("도움말입니다!");
  }
}
