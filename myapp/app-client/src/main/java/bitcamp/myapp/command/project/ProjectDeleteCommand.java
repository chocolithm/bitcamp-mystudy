package bitcamp.myapp.command.project;

import bitcamp.command.Command;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.util.Prompt;
import java.sql.Connection;

public class ProjectDeleteCommand implements Command {

  private ProjectDao projectDao;
  private Connection con;

  public ProjectDeleteCommand(ProjectDao projectDao, Connection con) {
    this.projectDao = projectDao;
    this.con = con;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    try {
      int projectNo = Prompt.inputInt("프로젝트 번호?");
      Project deletedProject = projectDao.findBy(projectNo);
      if (deletedProject == null) {
        System.out.println("없는 프로젝트입니다.");
        return;
      }

      con.setAutoCommit(false);
      projectDao.deleteMembers(projectNo);
      projectDao.delete(projectNo);
      con.commit();

      System.out.printf("%d번 프로젝트를 삭제 했습니다.\n", deletedProject.getNo());
    } catch (Exception e) {
      try {
        con.rollback();
      } catch (Exception ignored) {}
      System.out.println("프로젝트 데이터 삭제 중 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        con.setAutoCommit(true);
      } catch (Exception ignored) {}
    }
  }
}
