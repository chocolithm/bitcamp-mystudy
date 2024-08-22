package bitcamp.menu;

import bitcamp.net.Prompt;

public interface Menu {

  String getTitle();

  public void execute(Prompt prompt);
}
