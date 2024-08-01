package book.thisisjava.ch19.q07;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;
import book.thisisjava.ch19.q07.vo.Product;

public class ProductClient {

  List<Product> productList;

  public static void main(String[] args) {

    try {
      Socket socket = new Socket("localhost", 8888);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      ProductClient client = new ProductClient();
      JSONObject jsonObject;
      String json;
      String line = "------------------------------------------";

      while (true) {
        json = in.readUTF();
        jsonObject = new JSONObject(json);

        System.out.println(line);
        System.out.println("no\tname\t\t\t\tprice\t\tstock\n");
        System.out.println(line);
        client.printProducts(jsonObject);
        System.out.println(line);
        System.out.println("메뉴: 1.Create | 2.Update | 3.Delete | 4.Exit");

        Scanner scanner = new Scanner(System.in);
        System.out.print("선택: ");
        String menu = scanner.nextLine();

        switch (menu) {
          case "1":
            String data = scanner.nextLine();

            Product product = new Product();
            product.setName(scanner.nextLine());
            product.setPrice(scanner.nextDouble());
            product.setStock(scanner.nextInt());

            jsonObject = new JSONObject();
            jsonObject.put("menu", menu);
            jsonObject.put("data", product);
            json = jsonObject.toString();

            client.send(out, json);
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
  }

  private void printProducts(JSONObject jsonObject) {
    productList = (List<Product>) jsonObject.get("data");

    for(Product product : productList) {
      System.out.printf("%d\t%s\t\t%f\t%d개\n", product.getNo(), product.getName(), product.getPrice(), product.getStock());
    }
  }

  private void send(ObjectOutputStream out, String json) throws Exception{
    out.writeUTF(json);
    out.flush();
  }
}
