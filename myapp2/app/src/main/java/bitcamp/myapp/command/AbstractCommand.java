package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;

public abstract class AbstractCommand implements Command {

  String menuTitle;

  public AbstractCommand(String menuTitle) {
    this.menuTitle = menuTitle;
  }

  @Override
  public void execute() {
    String[] menus = getMenus();

    printMenus(menuTitle, menus);
    while (true) {
      String command = Prompt.input(String.format("메인/%s>", menuTitle));
      if (command.equals("menu")) {
        printMenus(menuTitle, menus);
        continue;
      } else if (command.equals("9")) { // 이전 메뉴 선택
        break;
      }

      try {
        int menuNo = Integer.parseInt(command);
        String subMenuTitle = getMenuTitle(menuNo, menus);
        if (subMenuTitle == null) {
          System.out.println("유효한 메뉴 번호가 아닙니다.");
        } else {
          processMenu(subMenuTitle);
        }
      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }

  @Override
  public void printMenus(String menuTitle, String[] menus) {
    System.out.printf("[%s]\n", menuTitle);
    for (int i = 0; i < menus.length; i++) {
      System.out.printf("%d. %s\n", (i + 1), menus[i]);
    }
    System.out.println("9. 이전");
  }

  @Override
  public String getMenuTitle(int menuNo, String[] menus) {
    return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
  }

  @Override
  public boolean isValidateMenu(int menuNo, String[] menus) {
    return menuNo >= 1 && menuNo <= menus.length;
  }

  protected abstract String[] getMenus();

  protected abstract void processMenu(String subMenuTitle);
}
