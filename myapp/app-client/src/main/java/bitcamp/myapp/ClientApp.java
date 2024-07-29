package bitcamp.myapp;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.myapp.listener.InitApplicationListener;
import bitcamp.util.Prompt;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientApp {

  List<ApplicationListener> listeners = new ArrayList<>();
  ApplicationContext appCtx = new ApplicationContext();

  public static void main(String[] args) {
    ClientApp app = new ClientApp();

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

  void execute() {

    try {
      Socket socket = new Socket("127.0.0.1", 8888);

      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

      appCtx.setAttributes("inputStream", in);
      appCtx.setAttributes("outputStream", out);

      // 애플리케이션이 시작될 때 리스너에게 알린다.
      for (ApplicationListener listener : listeners) {
        try {
          listener.onStartup(appCtx);
        } catch (Exception e) {
          System.out.println("리스너 실행 중 오류 발생!");
        }
      }

      System.out.println("[프로젝트 관리 시스템]");

      appCtx.getMainMenu().execute();

      out.writeUTF("quit");
      out.flush();

    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();
    }

    System.out.println("종료합니다.");

    Prompt.close();

    // 애플리케이션이 종료될 때 리스너에게 알린다.
    for (ApplicationListener listener : listeners) {
      try {
        listener.onShutdown(appCtx);
      } catch (Exception e) {
        System.out.println("리스너 실행 중 오류 발생!");
      }
    }
  }
}
