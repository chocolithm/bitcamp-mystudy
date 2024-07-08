package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;

public class ProjectCommand {

  ProjectList projectList = new ProjectList();
  UserList userList;

  public ProjectCommand(UserList userList) {
    this.userList = userList;
  }

  private void addMembers(Project project) {
    while (true) {
      int userNo = Prompt.inputInt("추가할 팀원 번호?(종료: 0)");
      if (userNo == 0) {
        break;
      }

      User user = userList.findByNo(userNo);
      if (user == null) {
        System.out.println("없는 팀원입니다.");
        continue;
      }

      if (project.getMembers().contains(user)) {
        System.out.printf("'%s'은 현재 팀원입니다.\n", user.getName());
        continue;
      }

      project.getMembers().add(user);
      System.out.printf("'%s'을 추가했습니다.\n", user.getName());
    }
  }

  private void deleteMembers(Project project) {
    for (int i = 0; i < project.getMembers().size(); i++) {
      User user = (User) project.getMembers().get(i);
      String str = Prompt.input("팀원(%s) 삭제?", user.getName());
      if (str.equalsIgnoreCase("y")) {
        project.getMembers().remove(i);
        System.out.printf("'%s' 팀원을 삭제합니다.\n", user.getName());
      } else {
        System.out.printf("'%s' 팀원을 유지합니다.\n", user.getName());
      }
    }
  }

  public void executeProjectCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        this.addProject();
        break;
      case "조회":
        this.viewProject();
        break;
      case "목록":
        this.listProject();
        break;
      case "변경":
        this.updateProject();
        break;
      case "삭제":
        this.deleteProject();
        break;
    }
  }

  private void addProject() {
    Project project = new Project();
    project.setTitle(Prompt.input("프로젝트명?"));
    project.setDescription(Prompt.input("설명?"));
    project.setStartDate(Prompt.input("시작일?"));
    project.setEndDate(Prompt.input("종료일?"));

    System.out.println("팀원:");
    addMembers(project);

    project.setNo(Project.getNextSeqNo());

    projectList.add(project);

    System.out.println("등록했습니다.");
  }

  private void listProject() {
    System.out.println("번호 프로젝트 기간");
    for (Object obj : projectList.toArray()) {
      Project project = (Project) obj;
      System.out.printf("%d %s %s ~ %s\n",
          project.getNo(), project.getTitle(), project.getStartDate(), project.getEndDate());
    }
  }

  private void viewProject() {
    int projectNo = Prompt.inputInt("프로젝트 번호?");
    Project project = projectList.findByNo(projectNo);
    if (project == null) {
      System.out.println("없는 프로젝트입니다.");
      return;
    }

    System.out.printf("프로젝트명: %s\n", project.getTitle());
    System.out.printf("설명: %s\n", project.getDescription());
    System.out.printf("기간: %s ~ %s\n", project.getStartDate(), project.getEndDate());
    System.out.println("팀원:");
    for (int i = 0; i < project.getMembers().size(); i++) {
      User user = (User) project.getMembers().get(i);
      System.out.printf("- %s\n", user.getName());
    }
  }

  private void updateProject() {
    int projectNo = Prompt.inputInt("프로젝트 번호?");
    Project project = projectList.findByNo(projectNo);
    if (project == null) {
      System.out.println("없는 프로젝트입니다.");
      return;
    }

    project.setTitle(Prompt.input("프로젝트명(%s)?", project.getTitle()));
    project.setDescription(Prompt.input("설명(%s)?", project.getDescription()));
    project.setStartDate(Prompt.input("시작일(%s)?", project.getStartDate()));
    project.setEndDate(Prompt.input("종료일(%s)?", project.getEndDate()));

    System.out.println("팀원:");
    deleteMembers(project);
    addMembers(project);

    System.out.println("변경 했습니다.");
  }

  private void deleteProject() {
    int projectNo = Prompt.inputInt("프로젝트 번호?");
    Project deletedProject = projectList.findByNo(projectNo);
    if (deletedProject != null) {
      projectList.remove(projectList.indexOf(deletedProject));
      System.out.printf("%d번 프로젝트를 삭제 했습니다.\n", deletedProject.getNo());
    } else {
      System.out.println("없는 프로젝트입니다.");
    }
  }

}
