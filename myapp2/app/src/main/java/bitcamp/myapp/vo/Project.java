package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Project implements Serializable, SequenceNo {

  private static int seqNo;

  private int no;
  private String title;
  private String description;
  private String startDate;
  private String endDate;
  private List<User> members;

  { // 인스턴스 블록
    members = new ArrayList<>();
  }

  public Project() {
  }

  public Project(int no) {
    this.no = no;
  }

  public static int getNextSeqNo() {
    return ++seqNo;
  }

  public static void initSeqNo(int no) {
    seqNo = no;
  }

  public static int getSeqNo() {
    return seqNo;
  }

  public static Project valueOf(String csv) {
    String[] values = csv.split(",");

    Project project = new Project();
    project.setNo(Integer.parseInt(values[0]));
    project.setTitle(values[1]);
    project.setDescription(values[2]);
    project.setStartDate(values[3]);
    project.setEndDate(values[4]);

    String[] members = values[5].split("#");
    for (String member : members) {
      String[] items = member.split("_");
      User user = new User();
      user.setNo(Integer.parseInt(items[0]));
      user.setName(items[1]);
      user.setEmail(items[2]);
      user.setPassword(items[3]);
      user.setTel(items[4]);
      project.getMembers().add(user);
    }

    return project;
  }

  public String toCsvString() {

    StringBuilder membersBuilder = new StringBuilder();
    for (User member : members) {
      if (membersBuilder.length() > 0) {
        membersBuilder.append("#");
      }
      membersBuilder.append(member.toCsvString().replaceAll(",", "_"));
    }

    return new StringBuilder()
        .append(no).append(",")
        .append(title).append(",")
        .append(description).append(",")
        .append(startDate).append(",")
        .append(endDate).append(",")
        .append(membersBuilder.toString())
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Project project = (Project) o;
    return no == project.no;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(no);
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

  public List<User> getMembers() {
    return members;
  }
}
