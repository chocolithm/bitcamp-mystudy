package bitcamp.myapp.util;

public class Stack extends LinkedList {
  public void push(Object value) {
    add(value);
  }

  public Object pop() {
    return remove(size() - 1);
  }

  public boolean isEmpty() {
    return size() == 0;
  }
}
