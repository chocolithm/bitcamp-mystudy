package bitcamp.myapp.util;

import java.util.Scanner;

public class Prompt {

  static Scanner keyboardScanner = new Scanner(System.in);
  static Queue inputQueue = new Queue();

  public static String input(String format, Object... args) {
    String promptTitle = String.format(format + " ", args);
    System.out.print(promptTitle);

    String input = keyboardScanner.nextLine();
    if (format.endsWith(">")) {
      inputQueue.offer(promptTitle + " " + input);
      if(inputQueue.size() > 20) {
        inputQueue.poll();
      }
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
    System.out.println("[명령내역]--------------------");
    for (int i = 0; i < inputQueue.size(); i++) {
      System.out.println(inputQueue.get(i));
    }
    System.out.println("--------------------------- 끝");
  }
}
