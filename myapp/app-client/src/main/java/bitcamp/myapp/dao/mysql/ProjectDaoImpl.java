package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectDaoImpl implements ProjectDao {

  private Connection con;
  private UserDao userDao;

  public ProjectDaoImpl(Connection con, UserDao userDao) {
    this.con = con;
    this.userDao = userDao;
  }

  @Override
  public boolean insert(Project project) throws Exception {
    try (Statement stmt = con.createStatement()) {

      String memberNoList = project.getMembers().stream()
          .map(user -> String.valueOf(user.getNo()))
          .collect(Collectors.joining(","));

      stmt.executeUpdate(String.format(
          "insert into myapp_projects(title, description, start_date, end_date, members)" +
              " values('%s', '%s', '%s', '%s', '%s')",
          project.getTitle(),
          project.getDescription(),
          project.getStartDate(),
          project.getEndDate(),
          memberNoList
      ));

      return true;
    }
  }

  @Override
  public List<Project> list() throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from myapp_projects order by project_id asc")) {

      ArrayList<Project> list = new ArrayList<>();

      while (rs.next()) {
        Project project = new Project();
        project.setNo(rs.getInt("project_id"));
        project.setTitle(rs.getString("title"));
        project.setStartDate(rs.getDate("start_date"));
        project.setEndDate(rs.getDate("end_date"));

        list.add(project);
      }

      return list;
    }
  }

  @Override
  public Project findBy(int no) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from myapp_projects where project_id = " + no)) {

      if (rs.next()) {
        Project project = new Project();
        project.setNo(rs.getInt("project_id"));
        project.setTitle(rs.getString("title"));
        project.setDescription(rs.getString("description"));
        project.setStartDate(rs.getDate("start_date"));
        project.setEndDate(rs.getDate("end_date"));

        String members = rs.getString("members");
        String[] memberNoList = members.split(",");
        for (String memberNo : memberNoList) {
          User user = userDao.findBy(Integer.parseInt(memberNo));
          if (user != null) {
            project.getMembers().add(user);
          }
        }

        return project;
      }

      return null;
    }
  }

  @Override
  public boolean update(Project project) throws Exception {
    try (Statement stmt = con.createStatement()) {

      String memberNoList = project.getMembers().stream()
          .map(user -> String.valueOf(user.getNo()))
          .collect(Collectors.joining(","));

      int count = stmt.executeUpdate(String.format(
          "update myapp_projects set" +
              " title = '%s'," +
              " description = '%s'," +
              " start_date = '%s'," +
              " end_date = '%s'," +
              " members = '%s'" +
              " where project_id = %d",
          project.getTitle(),
          project.getDescription(),
          project.getStartDate(),
          project.getEndDate(),
          memberNoList,
          project.getNo()
      ));

      return count > 0;
    }
  }

  @Override
  public boolean delete(int no) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int count = stmt.executeUpdate(String.format(
          "delete from myapp_projects where project_id = %d", no));

      return count > 0;
    }
  }
}
