package bitcamp.myapp.command.project;

import bitcamp.command.Command;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.util.Prompt;

public class ProjectAddCommand implements Command {

  private ProjectDao projectDao;
  private ProjectMemberHandler memberHandler;

  public ProjectAddCommand(ProjectDao projectDao, ProjectMemberHandler memberHandler) {
    this.projectDao = projectDao;
    this.memberHandler = memberHandler;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    try {
      Project project = new Project();
      project.setTitle(Prompt.input("프로젝트명?"));
      project.setDescription(Prompt.input("설명?"));
      project.setStartDate(Prompt.inputDate("시작일?(예: 2024-01-24)"));
      project.setEndDate(Prompt.inputDate("종료일?(예: 2024-02-15)"));

      System.out.println("팀원:");
      memberHandler.addMembers(project);

      projectDao.insert(project);
      projectDao.insertMembers(project.getNo(), project.getMembers());

      System.out.println("등록했습니다.");
    } catch (Exception e) {
      System.out.println("프로젝트 데이터 등록 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
