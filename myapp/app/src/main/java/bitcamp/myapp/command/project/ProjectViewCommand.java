package bitcamp.myapp.command.project;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import java.util.List;

public class ProjectViewCommand implements Command {

  private List<Project> projectList;

  public ProjectViewCommand(List<Project> projectList) {
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

    Project project = projectList.get(index);

    System.out.printf("프로젝트명: %s\n", project.getTitle());
    System.out.printf("설명: %s\n", project.getDescription());
    System.out.printf("기간: %s ~ %s\n", project.getStartDate(), project.getEndDate());
    System.out.println("팀원:");
    for (User user : project.getMembers()) {
      System.out.printf("- %s\n", user.getName());
    }
  }
}
