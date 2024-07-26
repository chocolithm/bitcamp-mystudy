package bitcamp.myapp;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.myapp.listener.InitApplicationListener;
import bitcamp.util.Prompt;
import java.util.ArrayList;
import java.util.List;

public class App {

  List<ApplicationListener> listeners = new ArrayList<>();
  ApplicationContext appCtx = new ApplicationContext();

  public static void main(String[] args) {
    App app = new App();

    // 애플리케이션이 시작되거나 종료될 때 알림 받을 객체의 연락처를 등록한다.
    app.addApplicationListener(new InitApplicationListener());

    app.execute();
  }

  private void addApplicationListener(ApplicationListener listener) {
    listeners.add(listener);
  }

  private void removeApplicationListener(ApplicationListener listener) {
    listeners.remove(listener);
  }

  private void execute() {

    for (ApplicationListener listener : listeners) {
      try {
        listener.onStartup(appCtx);
      } catch (Exception e) {
        System.out.println("리스너 실행 중 오류 발생");
      }
    }

    String appTitle = "[프로젝트 관리 시스템]";
    String line = "----------------------------------";

    try {
      appCtx.getMainMenu().execute();
    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();
    } finally {


    }

    System.out.println("종료합니다.");
    Prompt.close();

    for (ApplicationListener listener : listeners) {
      try {
        listener.onShutdown(appCtx);
      } catch (Exception e) {
        System.out.println("리스너 실행 중 오류 발생");
      }
    }
  }
}
