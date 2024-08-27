package bitcamp.myapp.command.project;

import bitcamp.command.Command;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.net.Prompt;
import org.apache.ibatis.session.SqlSessionFactory;

public class ProjectAddCommand implements Command {

  private ProjectDao projectDao;
  private ProjectMemberHandler memberHandler;
  private SqlSessionFactory sqlSessionFactory;

  public ProjectAddCommand(ProjectDao projectDao, ProjectMemberHandler memberHandler,
                           SqlSessionFactory sqlSessionFactory) {
    this.projectDao = projectDao;
    this.memberHandler = memberHandler;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    prompt.printf("[%s]\n", menuName);

    try {
      Project project = new Project();
      project.setTitle(prompt.input("프로젝트명?"));
      project.setDescription(prompt.input("설명?"));
      project.setStartDate(prompt.inputDate("시작일?(예: 2024-01-24)"));
      project.setEndDate(prompt.inputDate("종료일?(예: 2024-02-15)"));

      prompt.println("팀원:");
      memberHandler.addMembers(project, prompt);

      projectDao.insert(project);
      Thread.sleep(30000);
      if (project.getMembers() != null && project.getMembers().size() > 0) {
        projectDao.insertMembers(project.getNo(), project.getMembers());
        //throw new Exception("그냥 오류 발생!");
      }
      sqlSessionFactory.openSession(false).commit();

      prompt.println("등록했습니다.");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      prompt.println("등록 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
