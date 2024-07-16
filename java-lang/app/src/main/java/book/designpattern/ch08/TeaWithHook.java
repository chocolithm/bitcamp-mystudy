package book.designpattern.ch08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TeaWithHook extends CaffeineBeverage {
  @Override
  public void brew() {
    System.out.println("찻잎 우리는 중");
  }

  @Override
  public void addCondiments() {
    System.out.println("레몬 추가하는 중");
  }

  @Override
  public boolean customerWantsCondiments() {
    String answer = getUserInput();

    if(answer.toLowerCase().startsWith("y")) {
      return true;
    } else {
      return false;
    }
  }

  private String getUserInput() {
    String answer = null;

    System.out.println("차에 레몬을 넣을까요? (y/n)? ");

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    try {
      answer = in.readLine();
    } catch (IOException ioe) {
      System.out.println("IO 오류");
    }

    if(answer == null) {
      return "no";
    }

    return answer;
  }
}
