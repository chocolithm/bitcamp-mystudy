package bitcamp.myapp;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.listener.InitApplicationListener;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerApp {

  List<ApplicationListener> listeners = new ArrayList<>();
  ApplicationContext appCtx = new ApplicationContext();

  public static void main(String[] args) {
    try {
      ServerApp app = new ServerApp();
      app.addApplicationListener(new InitApplicationListener());
      app.execute();
    } catch (Exception e) {
      System.out.println("서버 실행 중 오류 발생");
      e.printStackTrace();
    }
  }

  private void addApplicationListener(ApplicationListener listener) {
    listeners.add(listener);
  }

  private void removeApplicationListener(ApplicationListener listener) {
    listeners.remove(listener);
  }

  private void execute() throws Exception {

    for (ApplicationListener listener : listeners) {
      try {
        if (!listener.onStartup(appCtx)) {
          System.out.println("종료합니다.");
          return;
        }
      } catch (Exception e) {
        System.out.println("리스너 실행 중 오류 발생!");
        e.printStackTrace();
      }
    }

    ServerSocket serverSocket = new ServerSocket(8888);
    System.out.println("서버 실행 중 ...");

    while (true) {
      service(serverSocket.accept());
    }

    // 애플리케이션이 종료될 때 리스너에게 알린다.
    //    for (ApplicationListener listener : listeners) {
    //      try {
    //        listener.onShutdown(appCtx);
    //      } catch (Exception e) {
    //        System.out.println("리스너 실행 중 오류 발생!");
    //      }
    //    }
  }

  private void service(Socket socket) {
    new Thread(() -> {
      try {
        Prompt prompt = new Prompt(socket, appCtx);

        prompt.println("[프로젝트 관리 시스템]");
        String email = prompt.input("email?");
        String password = prompt.input("password?");

        UserDao userDao = (UserDao) appCtx.getAttribute("userDao");
        User loginUser = userDao.findByEmailAndPassword(email, password);
        if (loginUser == null) {
          prompt.println("이메일 또는 암호가 맞지 않습니다.");
          prompt.print("<[goodbye!!]>");
          prompt.end();
          prompt.close();
          return;
        }

        prompt.setAttribute("loginUser", loginUser);
        appCtx.getMainMenu().execute(prompt);
        prompt.print("<[goodbye!!]>");
        prompt.end();
        prompt.close();
      } catch (Exception e) {
        System.out.println("실행 오류!");
        e.printStackTrace();
      }
    }).start();
  }
}
