package bitcamp.net;

import bitcamp.context.ApplicationContext;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Prompt {

  // 모든 클라이언트가 사용할 값 보관소
  ApplicationContext ctx;

  // 각 클라이언트가 접속하는 동안 사용할 수 있는 값 보관소
  private Map<String, Object> attributes = new HashMap<>();

  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;

  // 서버에서 출력하는 것을 임시 보관하는 역할
  private StringWriter strWriter = new StringWriter();

  // 문자열을 다양한 형식으로 출력할 있도록 도와주는 데코레이터
  // 이 객체를 사용하여 출력한 내용은 모두 위의 StringWriter에 보관된다.
  private PrintWriter printWriter = new PrintWriter(strWriter);

  public Prompt(Socket socket, ApplicationContext ctx) throws Exception {
    this.socket = socket;
    this.in = new DataInputStream(socket.getInputStream());
    this.out = new DataOutputStream(socket.getOutputStream());
    this.ctx = ctx;
  }

  public ApplicationContext getApplicationContext() {
    return this.ctx;
  }

  public void setAttribute(String name, Object value) {
    attributes.put(name, value);
  }

  public Object getAttribute(String name) {
    return attributes.get(name);
  }

  public String input(String format, Object... args) throws Exception {
    String promptTitle = String.format(format + " ", args);
    print(promptTitle);
    end();

    return in.readUTF();
  }

  public int inputInt(String format, Object... args) throws Exception {
    return Integer.parseInt(input(format, args));
  }

  public Date inputDate(String format, Object... args) throws Exception {
    return Date.valueOf(input(format, args));
  }

  public void print(String str) {
    printWriter.print(str);
  }

  public void printf(String format, Object... args) {
    printWriter.printf(format, args);
  }

  public void println(String str) {
    printWriter.println(str);
  }

  public void end() throws Exception {
    // 현재까지 출력한 내용을 꺼낸다.
    String message = strWriter.toString();

    // 클라이언트로 전송한다.
    out.writeUTF(message);
    out.flush();

    // StringWriter의 버퍼를 초기화한다.
    strWriter.getBuffer().setLength(0);
  }

  public void close() {
    try {
      in.close();
    } catch (Exception e) {
    }
    try {
      out.close();
    } catch (Exception e) {
    }
    try {
      socket.close();
    } catch (Exception e) {
    }
  }


}
