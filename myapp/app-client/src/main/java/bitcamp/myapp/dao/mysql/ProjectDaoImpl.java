package bitcamp.myapp.dao.mysql;

import bitcamp.bitbatis.SqlSession;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

  private SqlSession sqlSession;

  public ProjectDaoImpl(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public boolean insert(Project project) throws Exception {
    int keyNo = sqlSession.insertReturningKey(
        "insert into myapp_projects(title, description, start_date, end_date)" +
            " values(?, ?, ?, ?)",
        project.getTitle(),
        project.getDescription(),
        project.getStartDate(),
        project.getEndDate()
    );

    project.setNo(keyNo);
    return true;
  }

  @Override
  public List<Project> list() throws Exception {
    return sqlSession.selectList(
        "select" +
            " project_id as no," +
            " title," +
            " start_date as startDate," +
            " end_date as endDate" +
            " from myapp_projects order by project_id asc",
        Project.class
    );
  }

  @Override
  public Project findBy(int no) throws Exception {
    return sqlSession.selectOne(
        "select" +
            " project_id as no," +
            " title," +
            " description," +
            " start_date as startDate," +
            " end_date as endDate" +
            " from myapp_projects" +
            " where project_id = ?",
        Project.class,
        no
    );
  }

  @Override
  public boolean update(Project project) throws Exception {
    int count = sqlSession.update(
        "update myapp_projects"
            + " set title = ?, description = ?, start_date = ?, end_date = ?"
            + " where project_id = ?",
        project.getTitle(),
        project.getDescription(),
        project.getStartDate(),
        project.getEndDate(),
        project.getNo()
    );

    return count > 0;
  }

  @Override
  public boolean delete(int no) throws Exception {
    int count = sqlSession.delete(
        "delete from myapp_projects where project_id = ?",
        no
    );

    return count > 0;
  }

  @Override
  public boolean insertMembers(int projectNo, List<User> members) throws Exception {

    for (User user : members) {
      sqlSession.insert(
          "insert into myapp_project_members(project_id, user_id) values(?, ?)",
          projectNo,
          user.getNo()
      );
    }

    return true;
  }

  @Override
  public List<User> getMembers(int projectNo) throws Exception {

    return sqlSession.selectList(
        "select" +
            " pm.user_id as no," +
            " u.name" +
            " from myapp_project_members pm" +
            " inner join myapp_users u on pm.user_id = u.user_id" +
            " where pm.project_id = ?",
        User.class,
        projectNo
    );
  }

  @Override
  public boolean deleteMembers(int projectNo) throws Exception {
    int count = sqlSession.delete(
        "delete from myapp_project_members where project_id = ?",
        projectNo
    );

    return count > 0;
  }
}
