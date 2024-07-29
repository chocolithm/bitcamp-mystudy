package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ListProjectDao implements ProjectDao {
  List<Project> projectList = new ArrayList<>();
  int seqNo = 0;

  public ListProjectDao(UserDao userDao) {
    try (XSSFWorkbook workbook = new XSSFWorkbook("data.xlsx")) {
      XSSFSheet sheet = workbook.getSheet("projects");

      int lastRow = sheet.getLastRowNum();
      Project project;
      Row row;

      for (int i = 0; i < lastRow; i++) {
        project = new Project();
        row = sheet.getRow(i + 1);

        project.setNo(Integer.parseInt(row.getCell(0).getStringCellValue()));
        project.setTitle(row.getCell(1).getStringCellValue());
        project.setDescription(row.getCell(2).getStringCellValue());
        project.setStartDate(row.getCell(3).getStringCellValue());
        project.setEndDate(row.getCell(4).getStringCellValue());

        String[] memberNos = row.getCell(5).getStringCellValue().split(",");
        for (String memberNo : memberNos) {
          User user = userDao.findBy(Integer.parseInt(memberNo));
          if (user != null) {
            project.getMembers().add(user);
          }
        }

        projectList.add(project);
        if (project.getNo() > seqNo) {
          seqNo = project.getNo();
        }
      }
    } catch (Exception e) {
      System.out.println("프로젝트 데이터 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  public void save(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.createSheet("projects");

    Row row = sheet.createRow(0);
    String[] headers = {"no", "title", "description", "start_date", "end_date", "users"};
    for (int i = 0; i < headers.length; i++) {
      row.createCell(i).setCellValue(headers[i]);
    }

    int rowNo = 1;
    for (Project project : projectList) {
      row = sheet.createRow(rowNo++);

      row.createCell(0).setCellValue(String.valueOf(project.getNo()));
      row.createCell(1).setCellValue(project.getTitle());
      row.createCell(2).setCellValue(project.getDescription());
      row.createCell(3).setCellValue(project.getStartDate());
      row.createCell(4).setCellValue(project.getEndDate());

      StringBuilder users = new StringBuilder();
      for (User user : project.getMembers()) {
        if (!users.isEmpty()) {
          users.append(",");
        }
        users.append(user.getNo());
      }

      row.createCell(5).setCellValue(users.toString());
    }
  }

  @Override
  public boolean insert(Project project) {
    project.setNo(++seqNo);
    projectList.add(project);
    return true;
  }

  @Override
  public Project findBy(int no) {
    int index = projectList.indexOf(new Project(no));

    if (index > -1) {
      return projectList.get(index);
    }

    return null;
  }

  @Override
  public List<Project> list() {
    return projectList.stream().toList();
  }

  @Override
  public boolean update(Project project) {
    int index = projectList.indexOf(project);

    if (index > -1) {
      projectList.set(index, project);
      return true;
    }

    return false;
  }

  @Override
  public boolean delete(Project project) {
    int index = projectList.indexOf(project);

    if (index > -1) {
      return projectList.remove(project);
    }

    return false;
  }
}
