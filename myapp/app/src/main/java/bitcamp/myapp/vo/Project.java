package bitcamp.myapp.vo;

public class Project {
  private String title;
  private String description;
  private String startDate;
  private String endDate;
  private User[] members = new User[10];
  private int memberSize;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public User getMember(int index) {
    return members[index];
  }

  public int countMembers() {
    return memberSize;
  }

  public boolean containsMember(User user) {
    for (int i = 0; i < memberSize; i++) {
      User member = members[i];
      if (member.getName().equals(user.getName())) {
        return true;
      }
    }
    return false;
  }

  public void addMember(User user) {
    members[memberSize++] = user;
  }

  public void deleteMember(int index) {
    for (int i = index + 1; i < memberSize; i++) {
      members[i - 1] = members[i];
    }
    members[--memberSize] = null;
  }
}
