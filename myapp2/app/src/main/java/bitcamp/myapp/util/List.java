package bitcamp.myapp.util;

public interface List {
  public void add(Object obj);

  public Object remove(int index);

  public Object[] toArray();

  public int indexOf(Object obj);

  public int size();

  public Object get(int index);
}
