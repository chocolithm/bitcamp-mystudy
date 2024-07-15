package bitcamp.myapp.command;

import bitcamp.util.util.Prompt;

public class HistoryCommand implements Command {

  public void execute(String menuName) {
    Prompt.printHistory();
  }
}
