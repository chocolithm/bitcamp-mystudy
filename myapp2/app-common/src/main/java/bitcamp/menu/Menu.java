package bitcamp.menu;

import bitcamp.net.Prompt;

public interface Menu {

  String getTitle();

  void execute(Prompt prompt);
}
