package book.designpattern.ch09.iterator;

import java.util.Iterator;

public class DinerMenuIterator implements Iterator<MenuItem> {
  MenuItem[] items;
  int position = 0;

  public DinerMenuIterator(MenuItem[] items) {
    this.items = items;
  }

  @Override
  public MenuItem next() {
    MenuItem menuItem = items[position++];
    return menuItem;
  }

  @Override
  public boolean hasNext() {
    if(position >= items.length || items[position] == null) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("메뉴 항목은 지울 수 없습니다.");
  }
}
