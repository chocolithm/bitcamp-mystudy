package bitcamp.myapp.util;

public abstract class AbstractList implements List {

  protected int size;

  @Override
  public int size() {
    return size;
  }
}
