package bitcamp.myapp.command;

import bitcamp.myapp.util.Stack;

public interface Command {

  void execute(Stack menuPath);
}
