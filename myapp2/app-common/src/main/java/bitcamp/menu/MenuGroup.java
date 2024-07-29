package bitcamp.menu;

import bitcamp.util.Prompt;
import java.util.ArrayList;
import java.util.Stack;

public class MenuGroup extends AbstractMenu {
  private MenuGroup parent;
  private ArrayList<Menu> children;
  private String exitMenuTitle = "이전";

  public MenuGroup(String title) {
    super(title);
    children = new ArrayList<>();
  }

  public void execute() {

    String menuPath = getMenuPath();

    printMenu();

    while (true) {
      String command = Prompt.input("%s>", menuPath);
      if (command.equals("menu")) {
        printMenu();
        continue;
      } else if (command.equals("0")) {
        return;
      }

      try {
        int menuNo = Integer.parseInt(command);
        Menu menu = getMenu(menuNo - 1);

        if (menu == null) {
          System.out.println("유효한 메뉴 번호가 아닙니다.");
          continue;
        }

        menu.execute();

      } catch (NumberFormatException ex) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }

  public void setExitMenuTitle(String exitMenuTitle) {
    this.exitMenuTitle = exitMenuTitle;
  }

  void printMenu() {
    System.out.printf("[%s]\n", title);

    int i = 1;
    for (Menu menu : children) {
      System.out.printf("%d. %s\n", i++, menu.getTitle());
    }

    System.out.printf("0. %s\n", exitMenuTitle);
  }

  private String getMenuPath() {
    Stack<String> menuPathStack = new Stack<>();
    MenuGroup menuGroup = this;
    while (menuGroup != null) {
      menuPathStack.push(menuGroup.title);
      menuGroup = menuGroup.parent;
    }

    StringBuilder strBuilder = new StringBuilder();
    while (!menuPathStack.isEmpty()) {
      if (strBuilder.length() > 0) {
        strBuilder.append("/");
      }
      strBuilder.append(menuPathStack.pop());
    }

    return strBuilder.toString();
  }

  private void setParent(MenuGroup parent) {
    this.parent = parent;
  }

  public void add(Menu child) {
    if (child instanceof MenuGroup) {
      ((MenuGroup) child).setParent(this);
    }
    children.add(child);
  }

  public void remove(Menu child) {
    children.remove(child);
  }

  private Menu getMenu(int index) {
    if (index < 0 || index >= children.size()) {
      return null;
    }
    return children.get(index);
  }

  public int countMenus() {
    return children.size();
  }
}
