// Apache POI 라이브러리 사용법 - 엑셀 파일 읽기
package study.library.apache_poi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test11 {
  public static void main(String[] args) throws Exception {

    XSSFWorkbook workbook = new XSSFWorkbook("temp/test.xlsx");

    XSSFSheet sheet = workbook.getSheet("users");

    for (int i = 1; i < sheet.getLastRowNum(); i++) {

      Row row = sheet.getRow(i);

      System.out.printf("%d %s %s %s %s\n",
          (int) row.getCell(0).getNumericCellValue(),
          row.getCell(1).getStringCellValue(),
          row.getCell(2).getStringCellValue(),
          row.getCell(3).getStringCellValue(),
          row.getCell(4).getStringCellValue()
          );
    }

    System.out.println("엑셀 파일 읽기 완료!");
  }
}
