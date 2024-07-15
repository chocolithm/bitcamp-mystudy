package bitcamp.myapp.command.project;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Project;
import bitcamp.util.Prompt;
import java.util.List;

public class ProjectUpdateCommand implements Command {

  private List<Project> projectList;
  private ProjectMemberHandler memberHandler;

  public ProjectUpdateCommand(List<Project> projectList, ProjectMemberHandler memberHandler) {
    this.projectList = projectList;
    this.memberHandler = memberHandler;
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

    project.setTitle(Prompt.input("프로젝트명(%s)?", project.getTitle()));
    project.setDescription(Prompt.input("설명(%s)?", project.getDescription()));
    project.setStartDate(Prompt.input("시작일(%s)?", project.getStartDate()));
    project.setEndDate(Prompt.input("종료일(%s)?", project.getEndDate()));

    System.out.println("팀원:");
    memberHandler.deleteMembers(project);
    memberHandler.addMembers(project);

    System.out.println("변경 했습니다.");
  }
}
