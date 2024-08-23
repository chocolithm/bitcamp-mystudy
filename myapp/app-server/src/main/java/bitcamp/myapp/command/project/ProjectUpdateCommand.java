package bitcamp.myapp.command.project;

import bitcamp.command.Command;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSessionFactory;

public class ProjectUpdateCommand implements Command {

  private ProjectDao projectDao;
  private ProjectMemberHandler memberHandler;
  private SqlSessionFactory sqlSessionFactory;

  public ProjectUpdateCommand(ProjectDao projectDao, ProjectMemberHandler memberHandler,
      SqlSessionFactory sqlSessionFactory) {
    this.projectDao = projectDao;
    this.memberHandler = memberHandler;
    sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    prompt.printf("[%s]\n", menuName);

    try {
      int projectNo = prompt.inputInt("프로젝트 번호?");
      Project project = projectDao.findBy(projectNo);
      if (project == null) {
        prompt.println("없는 프로젝트입니다.");
        return;
      }

      project.setTitle(prompt.input("프로젝트명(%s)?", project.getTitle()));
      project.setDescription(prompt.input("설명(%s)?", project.getDescription()));
      project.setStartDate(prompt.inputDate("시작일(%s)?", project.getStartDate()));
      project.setEndDate(prompt.inputDate("종료일(%s)?", project.getEndDate()));

      prompt.println("팀원:");

      memberHandler.deleteMembers(project, prompt);
      memberHandler.addMembers(project, prompt);

      projectDao.update(project);
      projectDao.deleteMembers(projectNo);
      if (project.getMembers() != null && !project.getMembers().isEmpty()) {
        projectDao.insertMembers(projectNo, project.getMembers());
      }
      sqlSessionFactory.openSession(false).commit();
      prompt.println("변경 했습니다.");
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      prompt.println("프로젝트 데이터 변경 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
