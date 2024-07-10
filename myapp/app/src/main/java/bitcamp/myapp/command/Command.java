package bitcamp.myapp.command;

import java.util.Stack;

public interface Command {

  void execute(Stack menuPath);
}
