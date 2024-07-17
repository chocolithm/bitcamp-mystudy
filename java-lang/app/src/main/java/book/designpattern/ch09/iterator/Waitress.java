package book.designpattern.ch09.iterator;

import java.util.Iterator;
import java.util.List;

public class Waitress {
  List<Menu> menus;
  //  Menu pancakeHouseMenu;
  //  Menu dinerMenu;
  //  Menu cafeMenu;

  public Waitress(List<Menu> menus) {
    this.menus = menus;
  }

  public void printMenu() {
    Iterator<Menu> menuIterator = menus.iterator();
    while(menuIterator.hasNext()) {
      Menu menu = menuIterator.next();
      printMenu(menu.createIterator());
      System.out.println();
    }
  }

  public void printMenu(Iterator<MenuItem> iterator) {
    while(iterator.hasNext()) {
      MenuItem menuItem = iterator.next();
      System.out.print(menuItem.getName() + ", ");
      System.out.print(menuItem.getPrice() + " -- ");
      System.out.println(menuItem.getDescription());
    }
  }
}
