package book.designpattern.ch09.iterator;

import java.util.Iterator;

public interface Menu {
  public Iterator<MenuItem> createIterator();
}
