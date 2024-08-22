package bitcamp.myapp.command.project;

import bitcamp.command.Command;
import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.net.Prompt;

public class ProjectListCommand implements Command {

  private ProjectDao projectDao;

  public ProjectListCommand(ProjectDao projectDao) {
    this.projectDao = projectDao;
  }

  @Override
  public void execute(String menuName, Prompt prompt) {
    prompt.printf("[%s]\n", menuName);

    try {
      prompt.println("번호 프로젝트 기간");
      for (Project project : projectDao.list()) {
        prompt.printf("%d %s %s ~ %s\n",
            project.getNo(), project.getTitle(), project.getStartDate(), project.getEndDate());
      }
    } catch (Exception e) {
      prompt.println("프로젝트 목록 조회 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
