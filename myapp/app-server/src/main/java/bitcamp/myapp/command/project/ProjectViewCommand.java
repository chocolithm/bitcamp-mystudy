package bitcamp.myapp.command.project;

import bitcamp.command.Command;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;

public class ProjectViewCommand implements Command {

  private ProjectDao projectDao;

  public ProjectViewCommand(ProjectDao projectDao) {
    this.projectDao = projectDao;
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

      prompt.printf("프로젝트명: %s\n", project.getTitle());
      prompt.printf("설명: %s\n", project.getDescription());
      prompt.printf("기간: %s ~ %s\n", project.getStartDate(), project.getEndDate());
      prompt.println("팀원:");
      for (User user : project.getMembers()) {
        prompt.printf("- %s\n", user.getName());
      }
    } catch (Exception e) {
      prompt.println("프로젝트 조회 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
