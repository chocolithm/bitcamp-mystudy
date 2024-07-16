package book.thisisjava.ch11.q07;

public class WrongPasswordException extends Exception {
  public WrongPasswordException() {}

  public WrongPasswordException(String message) {
    super(message);
  }
}
