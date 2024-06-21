package bitcamp.myapp.vo;

public class Project {

  private static int seqNo;

  private int no;
  private String title;
  private String description;
  private String startDate;
  private String endDate;
  private User[] members = new User[10];
  private int memberSize;

  public static int getNextSeqNo() {
    return ++seqNo;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

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

  public boolean containsMember(User user) {
    for (int i = 0; i < this.memberSize; i++) {
      if (this.members[i] == user) {
        return true;
      }
    }
    return false;
  }

  public void addMember(User user) {
    this.members[this.memberSize++] = user;
  }

  public int countMembers() {
    return this.memberSize;
  }

  public User getMember(int index) {
    return this.members[index];
  }

  public void deleteMember(int index) {
    for (int i = index + 1; i < this.memberSize; i++) {
      this.members[i - 1] = this.members[i];
    }
    this.members[--this.memberSize] = null;
  }
}
