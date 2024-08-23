package bitcamp.menu;


import bitcamp.command.Command;
import bitcamp.net.Prompt;

public class MenuItem extends AbstractMenu {

  Command command;

  public MenuItem(String title) {
    super(title);
  }

  public MenuItem(String title, Command command) {
    super(title);
    this.command = command;
  }

  public void setCommand(Command command) {
    this.command = command;
  }

  @Override
  public void execute(Prompt prompt) {
    if (command != null) {
      command.execute(title, prompt);
    } else {
      prompt.println(title);
    }
  }
}
