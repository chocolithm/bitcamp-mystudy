package bitcamp.myapp;

import bitcamp.util.Prompt;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientApp {

  private static final String GOODBYE = "<!GOODBOY!>";

  public static void main(String[] args) {
    ClientApp app = new ClientApp();
    app.execute();
  }

  void execute() {

    String addr = Prompt.input("서버? ");
    int port = Prompt.inputInt("포트번호? ");

    try (Socket socket = new Socket(addr, port);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {

      while (true) {
        String message = in.readUTF();
        if (message.contains(GOODBYE)) {
          System.out.println(message.substring(0, message.indexOf(GOODBYE)));
          System.out.println("종료합니다.");
          break;
        }
        System.out.print(message);

        out.writeUTF(Prompt.input(""));
        out.flush();
      }

    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();
    }

    Prompt.close();

  }
}
