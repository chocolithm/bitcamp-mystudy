package bitcamp.myapp.command;

import bitcamp.myapp.util.Prompt;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.util.List;

public class ProjectCommand extends AbstractCommand {

  private List<Project> projectList;
  private List<User> userList;
  private String[] menus = {"등록", "목록", "조회", "변경", "삭제"};

  public ProjectCommand(String menuTitle, List<Project> projectList, List<User> userList) {
    super(menuTitle);
    this.projectList = projectList;
    this.userList = userList;
  }

  @Override
  protected String[] getMenus() {
    return menus;
  }

  @Override
  protected void processMenu(String menuName) {
    System.out.printf("[%s]\n", menuName);
    switch (menuName) {
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

  private void addMembers(Project project) {
    while (true) {
      int userNo = Prompt.inputInt("추가할 팀원 번호?(종료: 0)");
      if (userNo == 0) {
        break;
      }

      int index = userList.indexOf(new User(userNo));
      if (index == -1) {
        System.out.println("없는 팀원입니다.");
        continue;
      }

      User user = userList.get(index);
      if (project.getMembers().contains(user)) {
        System.out.printf("'%s'은 현재 팀원입니다.\n", user.getName());
        continue;
      }

      project.getMembers().add(user);
      System.out.printf("'%s'을 추가했습니다.\n", user.getName());
    }
  }

  private void deleteMembers(Project project) {
    Object[] members = project.getMembers().toArray();
    for (Object obj : members) {
      User member = (User) obj;
      String str = Prompt.input("팀원(%s) 삭제?", member.getName());
      if (str.equalsIgnoreCase("y")) {
        project.getMembers().remove(member);
        System.out.printf("'%s' 팀원을 삭제합니다.\n", member.getName());
      } else {
        System.out.printf("'%s' 팀원을 유지합니다.\n", member.getName());
      }
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
    for (Project project : projectList) {
      System.out.printf("%d %s %s ~ %s\n",
          project.getNo(), project.getTitle(), project.getStartDate(), project.getEndDate());
    }
  }

  private void viewProject() {
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

  private void updateProject() {
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
    deleteMembers(project);
    addMembers(project);

    System.out.println("변경 했습니다.");
  }

  private void deleteProject() {
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
