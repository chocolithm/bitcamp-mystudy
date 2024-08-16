package bitcamp.myapp.command.project;

import bitcamp.command.Command;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.util.Prompt;
import java.sql.Connection;

public class ProjectUpdateCommand implements Command {

  private ProjectDao projectDao;
  private ProjectMemberHandler memberHandler;
  private Connection con;

  public ProjectUpdateCommand(ProjectDao projectDao, ProjectMemberHandler memberHandler, Connection con) {
    this.projectDao = projectDao;
    this.memberHandler = memberHandler;
    this.con = con;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    try {
      int projectNo = Prompt.inputInt("프로젝트 번호?");
      Project project = projectDao.findBy(projectNo);
      if (project == null) {
        System.out.println("없는 프로젝트입니다.");
        return;
      }

      project.setTitle(Prompt.input("프로젝트명(%s)?", project.getTitle()));
      project.setDescription(Prompt.input("설명(%s)?", project.getDescription()));
      project.setStartDate(Prompt.inputDate("시작일(%s)?", project.getStartDate()));
      project.setEndDate(Prompt.inputDate("종료일(%s)?", project.getEndDate()));
      project.getMembers().addAll(projectDao.getMembers(projectNo));

      System.out.println("팀원:");

      con.setAutoCommit(false);
      memberHandler.deleteMembers(project);
      memberHandler.addMembers(project);
      con.commit();

      projectDao.update(project);
      projectDao.deleteMembers(projectNo);
      projectDao.insertMembers(projectNo, project.getMembers());
      System.out.println("변경 했습니다.");
    } catch (Exception e) {
      try {
        con.rollback();
      } catch (Exception ignored) {}
      System.out.println("프로젝트 데이터 변경 중 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        con.setAutoCommit(true);
      } catch (Exception ignored) {}
    }
  }
}
