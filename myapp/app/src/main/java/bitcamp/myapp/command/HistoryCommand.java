package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;

import java.util.Stack;

public class HistoryCommand implements Command {
  public void execute(Stack menuPath) {
    Prompt.printHistory();
  }
}
