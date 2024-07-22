package book.thisisjava.ch13.q03;

public class Container<K, V> {
  K name;
  V value;

  public void set(K name, V value) {
    this.name = name;
    this.value = value;
  }

  public K getKey() {
    return name;
  }

  public V getValue() {
    return value;
  }
}
