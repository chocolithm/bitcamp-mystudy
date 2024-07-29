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
  ApplicationContext context = new ApplicationContext();

  public static void main(String[] args) {
    ClientApp app = new ClientApp();

    app.addListener(new InitApplicationListener());

    app.execute();
  }

  private void addListener(ApplicationListener listener) {
    listeners.add(listener);
  }

  private void removeListener(ApplicationListener listener) {
    listeners.remove(listener);
  }

  void execute() {

    try {
      Socket socket = new Socket("127.0.0.1", 8888);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

      context.setAttributes("outputStream", out);
      context.setAttributes("inputStream", in);

      for (ApplicationListener listener : listeners) {
        try {
          listener.onStartup(context);
        } catch (Exception e) {
          System.out.println("리스너 실행 중 오류 발생!");
        }
      }

      System.out.println("프로젝트 관리 시스템 : 클라이언트");

      context.getMainMenu().execute();

      out.writeUTF("quit");
      out.flush();

    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();
    }

    System.out.println("종료합니다.");

    Prompt.close();

    for (ApplicationListener listener : listeners) {
      try {
        listener.onShutdown(context);
      } catch (Exception e) {
        System.out.println("리스너 실행 중 오류 발생!");
      }
    }
  }
}
