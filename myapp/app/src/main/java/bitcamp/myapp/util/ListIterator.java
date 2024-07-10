package bitcamp.myapp.util;

public class ListIterator implements Iterator {
  private List list;
  private int cursor;

  public ListIterator(List list) {
    this.list = list;
  }

  @Override
  public boolean hasNext() {
    return cursor < list.size();
  }

  @Override
  public Object next() {
    return list.get(cursor++);
  }
}
