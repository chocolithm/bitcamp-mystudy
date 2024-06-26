package bitcamp.myapp.util;

public interface List {
  public abstract void add(Object obj);
  public abstract Object remove(int index);
  public abstract Object[] toArray();
  public abstract int indexOf(Object obj);
  public abstract int size();
  public abstract Object get(int index);
}
