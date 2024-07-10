package bitcamp.myapp.util;

public abstract class AbstractList implements List {

  protected int size = 0;

  @Override
  public int size() {
    return this.size;
  }

  public Iterator iterator() {
    return new ListIterator(this);
  }

  public boolean contains(Object obj) {
    return indexOf(obj) != -1;
  }
}
