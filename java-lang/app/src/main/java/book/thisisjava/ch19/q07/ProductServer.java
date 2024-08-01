package book.thisisjava.ch19.q07;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import book.thisisjava.ch19.q07.vo.Product;

public class ProductServer {

  private static List<Product> productList = new ArrayList<Product>();
  private static final String SUCCESS = "success";

  public static void main(String[] args) {

    Thread thread = new Thread(() -> {
      try {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ProductServer server = new ProductServer();
        String json;
        JSONObject jsonObject;

        while (true) {
          jsonObject = new JSONObject();
          jsonObject.put("status", SUCCESS);
          jsonObject.put("data", productList);
          json = jsonObject.toString();
          server.send(out, json);

          json = in.readUTF();
          jsonObject = new JSONObject(json);

          String menu = jsonObject.getString("menu");
          Object data = jsonObject.get("data");

          switch (menu) {
            case "1":
              Product product = (Product) data;
              product.setNo(productList.size() + 1);
              productList.add(product);
              break;
            case "2":
              break;
            case "3":
              break;
            case "4":
              Thread.interrupted();
              break;
            default:
              System.out.println("처리할 수 없는 명령입니다.");
          }
        }

      } catch (Exception e) {

      }
    });

    thread.start();
  }

  private void send(ObjectOutputStream out, String json) throws Exception{
    out.writeUTF(json);
    out.flush();
  }
}
