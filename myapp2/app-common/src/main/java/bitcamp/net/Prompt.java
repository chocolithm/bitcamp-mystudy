package bitcamp.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Prompt {

  private Map<String, Object> attributes = new HashMap<>();

  private Socket socket;
  private DataOutputStream out;
  private DataInputStream in;

  private StringWriter stringWriter = new StringWriter();
  private PrintWriter printWriter = new PrintWriter(stringWriter);

  public Prompt(Socket socket) throws Exception {
    this.socket = socket;
    out = new DataOutputStream(socket.getOutputStream());
    in = new DataInputStream(socket.getInputStream());
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

  public void println(String str) {
    printWriter.println(str);
  }

  public void printf(String format, Object... args) {
    printWriter.printf(format, args);
  }

  public void end() throws Exception {
    String message = stringWriter.toString();
    out.writeUTF(message);
    out.flush();

    stringWriter.getBuffer().setLength(0);
  }

  public void close() {
    try {
      out.close();
    } catch (Exception ignored) {
    }
    try {
      in.close();
    } catch (Exception ignored) {
    }
    try {
      socket.close();
    } catch (Exception ignored) {
    }
  }
}
