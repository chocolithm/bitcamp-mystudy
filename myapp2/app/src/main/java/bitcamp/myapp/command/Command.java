package bitcamp.myapp.command;

public interface Command {
  void execute();

  void printMenus(String menuTitle, String[] menus);

  String getMenuTitle(int menuNo, String[] menus);

  boolean isValidateMenu(int menuNo, String[] menus);
}
