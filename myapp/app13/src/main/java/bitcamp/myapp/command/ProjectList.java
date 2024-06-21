package bitcamp.myapp.command;

import bitcamp.myapp.vo.Project;

public class ProjectList {

  private static final int MAX_SIZE = 100;
  private Project[] projects = new Project[MAX_SIZE];
  private int projectLength = 0;

  public void add(Project project) {
    this.projects[this.projectLength++] = project;
  }

  public Project delete(int projectNo) {
    Project deletedProject = findByNo(projectNo);
    if (deletedProject == null) {
      return null;
    }

    int index = indexOf(deletedProject);
    for (int i = index + 1; i < this.projectLength; i++) {
      this.projects[i - 1] = this.projects[i];
    }
    this.projects[--this.projectLength] = null;
    return deletedProject;
  }

  public Project[] toArray() {
    Project[] arr = new Project[this.projectLength];
    for (int i = 0; i < this.projectLength; i++) {
      arr[i] = this.projects[i];
    }
    return arr;
  }

  public Project findByNo(int projectNo) {
    for (int i = 0; i < this.projectLength; i++) {
      if (this.projects[i].getNo() == projectNo) {
        return this.projects[i];
      }
    }
    return null;
  }

  public int indexOf(Project project) {
    for (int i = 0; i < this.projectLength; i++) {
      if (project == this.projects[i]) {
        return i;
      }
    }
    return -1;
  }
}
