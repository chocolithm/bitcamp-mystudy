package book.thisisjava.ch16.q08;

@FunctionalInterface
public interface Function<T> {
  public double apply(T t);
}
