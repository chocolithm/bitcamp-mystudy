package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

  private Connection con;

  public ProjectDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public boolean insert(Project project) throws Exception {
    try (Statement stmt = con.createStatement()) {

      String members = "";
      for (User member : project.getMembers()) {
        if (!members.isEmpty()) {
          members += ",";
        }
        members += member.getNo();
      }

      stmt.executeUpdate(String.format(
          "insert into myapp_projects(title, description, start_date, end_date, members)" +
              " values('%s', '%s', '%s', '%s', '%s')",
          project.getTitle(),
          project.getDescription(),
          project.getStartDate(),
          project.getEndDate(),
          members
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
        project.setStartDate(rs.getString("start_date"));
        project.setEndDate(rs.getString("end_date"));

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
        project.setStartDate(rs.getString("start_date"));
        project.setEndDate(rs.getString("end_date"));

        try (Statement stmt_member = con.createStatement();
            ResultSet rs_member = stmt_member.executeQuery(
                String.format("select * from myapp_users where user_id in (%s)",
                    rs.getString("members")));) {

          while (rs_member.next()) {
            User user = new User();
            user.setNo(rs_member.getInt("user_id"));
            user.setName(rs_member.getString("name"));
            user.setEmail(rs_member.getString("email"));
            user.setTel(rs_member.getString("tel"));

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

      String members = "";
      for (User member : project.getMembers()) {
        if (!members.isEmpty()) {
          members += ",";
        }
        members += member.getNo();
      }

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
          members,
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
