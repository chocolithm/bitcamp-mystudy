package bitcamp.myapp;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.myapp.dao.skel.BoardDaoSkel;
import bitcamp.myapp.dao.skel.ProjectDaoSkel;
import bitcamp.myapp.dao.skel.UserDaoSkel;
import bitcamp.myapp.listener.InitApplicationListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerApp {

  List<ApplicationListener> listeners = new ArrayList<>();
  ApplicationContext context = new ApplicationContext();

  public static void main(String[] args) {
    ServerApp app = new ServerApp();

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

      ServerSocket serverSocket = new ServerSocket(8888);
      System.out.println("서버 실행 중 ...");

      Socket socket = serverSocket.accept();
      System.out.println("클라이언트 연결됨");

      for (ApplicationListener listener : listeners) {
        try {
          listener.onStartup(context);
        } catch (Exception e) {
          System.out.println("리스너 실행 중 오류 발생!");
        }
      }

      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

      context.setAttributes("inputStream", in);
      context.setAttributes("outputStream", out);

      UserDaoSkel userDaoSkel = (UserDaoSkel) context.getAttribute("userDaoSkel");
      ProjectDaoSkel projectDaoSkel = (ProjectDaoSkel) context.getAttribute("projectDaoSkel");
      BoardDaoSkel boardDaoSkel = (BoardDaoSkel) context.getAttribute("boardDaoSkel");

      while (true) {
        String dataName = in.readUTF();

        if (dataName.equals("quit")) {
          break;
        }

        switch (dataName) {
          case "users":
            userDaoSkel.service(in, out);
            break;
          case "projects":
            projectDaoSkel.service(in, out);
            break;
          case "boards":
            boardDaoSkel.service(in, out);
            break;
          default:
        }
      }

    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();
    }

    System.out.println("종료합니다.");
  }
}
