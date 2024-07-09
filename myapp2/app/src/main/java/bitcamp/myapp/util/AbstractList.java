package bitcamp.myapp.util;

public abstract class AbstractList implements List {

  protected int size = 0;

  @Override
  public int size() {
    return this.size;
  }
}
