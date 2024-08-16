package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into myapp_projects(title, description, start_date, end_date)" +
            " values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, project.getTitle());
      stmt.setString(2, project.getDescription());
      stmt.setDate(3, project.getStartDate());
      stmt.setDate(4, project.getEndDate());

      stmt.executeUpdate();

      ResultSet keyRS = stmt.getGeneratedKeys();
      keyRS.next();
      int projectNo = keyRS.getInt(1);
      project.setNo(projectNo);

      return true;
    }
  }

  @Override
  public List<Project> list() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement("select * from myapp_projects order by project_id asc");
        ResultSet rs = stmt.executeQuery()) {

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
    try (PreparedStatement stmt = con.prepareStatement(
        "select project_id, title, description, start_date, end_date"
        + " from myapp_projects where project_id = ?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Project project = new Project();
          project.setNo(rs.getInt("project_id"));
          project.setTitle(rs.getString("title"));
          project.setDescription(rs.getString("description"));
          project.setStartDate(rs.getDate("start_date"));
          project.setEndDate(rs.getDate("end_date"));

          return project;
        }
      }

      return null;
    }
  }

  @Override
  public boolean update(Project project) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update myapp_projects"
            + " set title = ?, description = ?, start_date = ?, end_date = ?"
            + " where project_id = ?")) {

      stmt.setString(1, project.getTitle());
      stmt.setString(2, project.getDescription());
      stmt.setDate(3, project.getStartDate());
      stmt.setDate(4, project.getEndDate());
      stmt.setInt(5, project.getNo());

      return stmt.executeUpdate() > 0;
    }
  }

  @Override
  public boolean delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from myapp_projects where project_id = ?")) {

      stmt.setInt(1, no);

      return stmt.executeUpdate() > 0;
    }
  }

  @Override
  public boolean insertMembers(int projectNo, List<User> members) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into myapp_project_members(project_id, user_id) values(?, ?)")) {

      for (User user : members) {
        stmt.setInt(1, projectNo);
        stmt.setInt(2, user.getNo());

        stmt.executeUpdate();
      }

      return true;
    }
  }

  @Override
  public List<User> getMembers(int projectNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select pm.user_id, u.name" +
        " from myapp_project_members pm" +
        " inner join myapp_users u on pm.user_id = u.user_id" +
        " where pm.project_id = ?")) {

      stmt.setInt(1, projectNo);

      try (ResultSet rs = stmt.executeQuery()) {
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
  }

  @Override
  public boolean deleteMembers(int projectNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement("delete from myapp_project_members where project_id = ?")) {

      stmt.setInt(1, projectNo);

      return stmt.executeUpdate() > 0;
    }
  }
}
