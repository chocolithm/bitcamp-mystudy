package book.thisisjava.ch16.q09;

@FunctionalInterface
public interface Function<T> {
  public double apply(T t);
}
