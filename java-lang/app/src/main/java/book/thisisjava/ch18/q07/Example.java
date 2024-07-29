package book.thisisjava.ch18.q07;

import java.io.BufferedReader;
import java.io.FileReader;

public class Example {
  public static void main(String[] args) throws Exception {
    String filePath = "C:\\Users\\BITCAMP\\Downloads\\Example.java";

    FileReader fr = new FileReader(filePath);
    BufferedReader br = new BufferedReader(fr);

    int rowNumber = 0;
    String rowData;
    while(true) {
      String line = br.readLine();
      if(line == null) {
        break;
      }
      System.out.println(++rowNumber + ": " + line);
    }

    br.close();
    fr.close();
  }
}
