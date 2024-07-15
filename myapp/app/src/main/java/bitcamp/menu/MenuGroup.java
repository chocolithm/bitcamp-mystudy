package bitcamp.menu;

import bitcamp.util.Prompt;
import java.util.ArrayList;
import java.util.Stack;

public class MenuGroup extends AbstractMenu {

  private MenuGroup parent;
  private Stack<String> menuPath;
  private ArrayList<Menu> children = new ArrayList<>();
  private String exitMenuTitle = "이전";

  public MenuGroup(String title) {
    super(title);
    menuPath = new Stack<>();
  }

  @Override
  public void execute() {
    menuPath.push(title);

    printMenus();

    while (true) {
      String command = Prompt.input("%s>", getMenuPathTitle());
      if (command.equals("menu")) {
        printMenus();
        continue;
      } else if (command.equals("0")) { // 이전 메뉴 선택
        menuPath.pop();
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

  public void setExitMenuTitle(String title) {
    exitMenuTitle = title;
  }

  private void printMenus() {
    System.out.printf("[%s]\n", title);
    int i = 1;
    for (Menu menu : children) {
      System.out.printf("%d. %s\n", i++, menu.getTitle());
    }
    System.out.printf("0. %s\n", exitMenuTitle);
  }

  private String getMenuPathTitle() {
    StringBuilder strBuilder = new StringBuilder();
    for (int i = 0; i < menuPath.size(); i++) {
      if (strBuilder.length() > 0) {
        strBuilder.append("/");
      }
      strBuilder.append(menuPath.get(i));
    }
    return strBuilder.toString();
  }

  private void setParent(MenuGroup parent) {
    this.parent = parent;
    this.menuPath = parent.menuPath;
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

  public Menu getMenu(int index) {
    if (index < 0 || index >= children.size()) {
      return null;
    }
    return children.get(index);
  }

  public int countMenus() {
    return children.size();
  }
}
