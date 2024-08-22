package bitcamp.command;

import bitcamp.net.Prompt;

public interface Command {

  void execute(String menuName, Prompt prompt);
}
