package bitcamp.myapp.command;

import bitcamp.command.Command;
import bitcamp.net.Prompt;

public class HelloCommand implements Command {

  public void execute(String menuName, Prompt prompt) {
    try {
      String name = prompt.input("이름은?");
      prompt.println(name + "님 반갑습니다!");
    } catch (Exception e) {
      //
    }
  }
}
