package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.util.Stack;

public class HistoryCommand implements Command {
  public void execute(Stack menuPath) {
    Prompt.printHistory();
  }
}
