package bitcamp.myapp.dao;

import bitcamp.myapp.vo.User;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ListUserDao implements UserDao {
  List<User> userList = new ArrayList<>();
  int seqNo = 0;

  public ListUserDao() {
    try (XSSFWorkbook workbook = new XSSFWorkbook("data.xlsx")) {
      XSSFSheet sheet = workbook.getSheet("users");

      int lastRow = sheet.getLastRowNum();
      User user;
      Row row;

      for (int i = 0; i < lastRow; i++) {
        user = new User();
        row = sheet.getRow(i + 1);

        user.setNo(Integer.parseInt(row.getCell(0).getStringCellValue()));
        user.setName(row.getCell(1).getStringCellValue());
        user.setEmail(row.getCell(2).getStringCellValue());
        user.setPassword(row.getCell(3).getStringCellValue());
        user.setTel(row.getCell(4).getStringCellValue());

        userList.add(user);
        if (user.getNo() > seqNo) {
          seqNo = user.getNo();
        }
      }
    } catch (Exception e) {
      System.out.println("회원 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  public void save(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.createSheet("users");

    Row row = sheet.createRow(0);
    String[] headers = {"no", "name", "email", "password", "tel"};
    for (int i = 0; i < headers.length; i++) {
      row.createCell(i).setCellValue(headers[i]);
    }

    int rowNo = 1;
    for (User user : userList) {
      row = sheet.createRow(rowNo++);

      row.createCell(0).setCellValue(String.valueOf(user.getNo()));
      row.createCell(1).setCellValue(user.getName());
      row.createCell(2).setCellValue(user.getEmail());
      row.createCell(3).setCellValue(user.getPassword());
      row.createCell(4).setCellValue(user.getTel());
    }
  }

  @Override
  public boolean insert(User user) {
    user.setNo(++seqNo);
    userList.add(user);
    return true;
  }

  @Override
  public User findBy(int no) {
    int index = userList.indexOf(new User(no));

    if (index > -1) {
      return userList.get(index);
    }

    return null;
  }

  @Override
  public List<User> list() {
    return userList.stream().toList();
  }

  @Override
  public boolean update(User user) {
    int index = userList.indexOf(user);

    if (index > -1) {
      userList.set(index, user);
      return true;
    }

    return false;
  }

  @Override
  public boolean delete(User user) {
    int index = userList.indexOf(user);

    if (index > -1) {
      return userList.remove(user);
    }

    return false;
  }
}
