package book.thisisjava.ch13.q02;

public class Container<T> {
  T content;

  public void set(T content) {
    this.content = content;
  }

  public T get() {
    return content;
  }
}
