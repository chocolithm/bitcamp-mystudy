package bitcamp.myapp.util;

import java.util.Scanner;

public class Prompt {

  static Scanner keyboardScanner = new Scanner(System.in);
  static Queue history = new Queue();

  public static String input(String format, Object... args) {
    String promptInput = String.format(format + " ", args);
    System.out.printf(promptInput);

    String input = keyboardScanner.nextLine();

    if (format.endsWith(">")) {
      history.offer(promptInput + input);
    }

    if (history.size() > 10) {
      history.pull();
    }

    return input;
  }

  public static int inputInt(String format, Object... args) {
    return Integer.parseInt(input(format, args));
  }

  public static void close() {
    keyboardScanner.close();
  }

  public static void printHistory() {
    System.out.println("[명령내역]----------------------");
    for (int i = 0; i < history.size(); i++) {
      System.out.println(history.get(i));
    }
    System.out.println("----------------------------- 끝");
  }
}
