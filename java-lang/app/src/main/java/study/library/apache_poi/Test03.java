// Apache POI 라이브러리 사용법 - 엑셀 Workbook + Sheet + Row 만들기
package study.library.apache_poi;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test03 {
  public static void main(String[] args) throws Exception {

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("users");

    List<Object[]> data = new ArrayList<Object[]>();
    data.add(new Object[] {"no", "name", "email", "password", "tel"});
    data.add(new Object[] {1, "user1", "user1@test.com", "1111", "010-1111-1111"});
    data.add(new Object[] {2, "user2", "user2@test.com", "2222", "010-1111-2222"});
    data.add(new Object[] {3, "user3", "user3@test.com", "3333", "010-1111-3333"});
    data.add(new Object[] {4, "user4", "user4@test.com", "4444", "010-1111-4444"});
    data.add(new Object[] {5, "user5", "user5@test.com", "5555", "010-1111-5555"});

    for (int i = 0; i < data.size(); i++) {

      Object[] values = data.get(i);
      Row row = sheet.createRow(i);

      for(int j = 0; j < values.length; j++) {
        if(values[j] instanceof String) {
          row.createCell(j).setCellValue((String) values[j]);
        } else if(values[j] instanceof Integer) {
          row.createCell(j).setCellValue((Integer) values[j]);
        }

      }
    }

    try (FileOutputStream out = new FileOutputStream("temp/test.xlsx")) {
      workbook.write(out);
    }

    System.out.println("엑셀 파일 생성 완료!");
  }
}
