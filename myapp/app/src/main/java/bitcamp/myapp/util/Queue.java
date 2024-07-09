package bitcamp.myapp.util;

public class Queue extends LinkedList {
  public void offer(Object value) {
    add(value);
  }

  public Object poll() {
    return remove(0);
  }

  public boolean isEmpty() {
    return size() == 0;
  }
}
