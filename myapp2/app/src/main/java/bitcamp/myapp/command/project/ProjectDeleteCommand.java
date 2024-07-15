package bitcamp.myapp.command.project;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Project;
import bitcamp.util.Prompt;
import java.util.List;

public class ProjectDeleteCommand implements Command {

  private List<Project> projectList;

  public ProjectDeleteCommand(List<Project> projectList) {
    this.projectList = projectList;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    int projectNo = Prompt.inputInt("프로젝트 번호?");
    int index = projectList.indexOf(new Project(projectNo));
    if (index == -1) {
      System.out.println("없는 프로젝트입니다.");
      return;
    }

    Project deletedProject = projectList.remove(index);
    System.out.printf("%d번 프로젝트를 삭제 했습니다.\n", deletedProject.getNo());
  }
}
