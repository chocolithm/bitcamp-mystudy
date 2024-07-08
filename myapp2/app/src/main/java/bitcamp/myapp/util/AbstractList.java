package bitcamp.myapp.util;

public abstract class AbstractList implements List {
  protected int size = 0;

  public int size() {
    return this.size;
  }
}
