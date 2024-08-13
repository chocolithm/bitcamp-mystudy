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

      stmt.executeUpdate(String.format(
          "insert into myapp_projects(title, description, start_date, end_date)" +
              " values('%s', '%s', '%s', '%s')",
          project.getTitle(),
          project.getDescription(),
          project.getStartDate(),
          project.getEndDate()
      ), Statement.RETURN_GENERATED_KEYS);

      ResultSet keyRS = stmt.getGeneratedKeys();
      keyRS.next();
      int projectNo = keyRS.getInt(1);
      project.setNo(projectNo);

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

        return project;
      }

      return null;
    }
  }

  @Override
  public boolean update(Project project) throws Exception {
    try (Statement stmt = con.createStatement()) {

      int count = stmt.executeUpdate(String.format(
          "update myapp_projects set" +
              " title = '%s'," +
              " description = '%s'," +
              " start_date = '%s'," +
              " end_date = '%s'" +
              " where project_id = %d",
          project.getTitle(),
          project.getDescription(),
          project.getStartDate(),
          project.getEndDate(),
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

  @Override
  public boolean insertMembers(int projectNo, List<User> members) throws Exception {
    try (Statement stmt = con.createStatement()) {

      for (User user : members) {
        stmt.executeUpdate(String.format(
            "insert into myapp_project_members(project_id, user_id)" +
                " values(%d, %d)",
            projectNo,
            user.getNo()
        ));
      }

      return true;
    }
  }

  @Override
  public List<User> getMembers(int projectNo) throws Exception {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select" +
                " pm.user_id," +
                " u.name" +
                " from myapp_project_members pm" +
                " inner join myapp_users u on pm.user_id = u.user_id" +
                " where pm.project_id = " + projectNo)) {

      ArrayList<User> list = new ArrayList<>();
      while (rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        list.add(user);
      }

      return list;
    }
  }

  @Override
  public boolean deleteMembers(int projectNo) throws Exception {
    try (Statement stmt = con.createStatement()) {
      int count = stmt.executeUpdate(
          String.format("delete from myapp_project_members where project_id = %d", projectNo));
      return count > 0;
    }
  }
}
