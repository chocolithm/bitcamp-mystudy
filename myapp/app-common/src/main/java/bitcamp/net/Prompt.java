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

  ApplicationContext ctx;

  private Map<String, Object> attributes = new HashMap<>();

  private Socket socket;
  private DataInputStream in;
  private DataOutputStream out;

  private StringWriter strWriter = new StringWriter();
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

  public int inputInt(String format, Object... args) throws Exception {
    return Integer.parseInt(input(format, args));
  }

  public Date inputDate(String format, Object... args) throws Exception {
    return Date.valueOf(input(format, args));
  }

  public String input(String format, Object... args) throws Exception {
    String promptTitle = String.format(format + " ", args);
    print(promptTitle);
    end();

    return in.readUTF();
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
    String message = strWriter.toString();
    out.writeUTF(message);
    out.flush();

    strWriter.getBuffer().setLength(0);
  }

  public void close() {
    try {
      in.close();
    } catch (Exception ignored) {
    }
    try {
      out.close();
    } catch (Exception ignored) {
    }
    try {
      socket.close();
    } catch (Exception ignored) {
    }
  }
}
