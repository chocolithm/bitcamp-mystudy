package bitcamp.myapp.command.project;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Project;
import bitcamp.util.Prompt;
import java.util.List;

public class ProjectAddCommand implements Command {

  private List<Project> projectList;
  private ProjectMemberHandler memberHandler;

  public ProjectAddCommand(List<Project> projectList, ProjectMemberHandler memberHandler) {
    this.projectList = projectList;
    this.memberHandler = memberHandler;
  }

  @Override
  public void execute(String menuName) {
    System.out.printf("[%s]\n", menuName);

    Project project = new Project();
    project.setTitle(Prompt.input("프로젝트명?"));
    project.setDescription(Prompt.input("설명?"));
    project.setStartDate(Prompt.input("시작일?"));
    project.setEndDate(Prompt.input("종료일?"));

    System.out.println("팀원:");
    memberHandler.addMembers(project);

    project.setNo(Project.getNextSeqNo());

    projectList.add(project);

    System.out.println("등록했습니다.");
  }
}
