package bitcamp.myapp.vo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Project {

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

  public static Project valueOf(byte[] bytes) throws IOException {
    try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
      Project project = new Project();
      byte[] buf = new byte[1000];
      int len;

      project.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

      len = in.read() << 8 | in.read();
      in.read(buf, 0, len);
      project.setTitle(new String(buf, 0, len, StandardCharsets.UTF_8));

      len = in.read() << 8 | in.read();
      in.read(buf, 0, len);
      project.setDescription(new String(buf, 0, len, StandardCharsets.UTF_8));

      len = in.read() << 8 | in.read();
      in.read(buf, 0, len);
      project.setStartDate(new String(buf, 0, len, StandardCharsets.UTF_8));

      len = in.read() << 8 | in.read();
      in.read(buf, 0, len);
      project.setEndDate(new String(buf, 0, len, StandardCharsets.UTF_8));

      int memberLength = in.read() << 8 | in.read();
      for (int i = 0; i < memberLength; i++) {
        len = (in.read() << 8) | in.read();
        bytes = new byte[len];
        in.read(bytes);

        User user = User.valueOf(bytes);
        project.getMembers().add(user);
      }

      return project;
    }
  }

  public byte[] getBytes() throws IOException {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      out.write(no >> 24);
      out.write(no >> 16);
      out.write(no >> 8);
      out.write(no);

      byte[] bytes = title.getBytes(StandardCharsets.UTF_8);
      out.write(bytes.length >> 8);
      out.write(bytes.length);
      out.write(title.getBytes(StandardCharsets.UTF_8));

      bytes = description.getBytes(StandardCharsets.UTF_8);
      out.write(bytes.length >> 8);
      out.write(bytes.length);
      out.write(description.getBytes(StandardCharsets.UTF_8));

      bytes = startDate.getBytes(StandardCharsets.UTF_8);
      out.write(bytes.length >> 8);
      out.write(bytes.length);
      out.write(startDate.getBytes(StandardCharsets.UTF_8));

      bytes = endDate.getBytes(StandardCharsets.UTF_8);
      out.write(bytes.length >> 8);
      out.write(bytes.length);
      out.write(endDate.getBytes(StandardCharsets.UTF_8));

      out.write(members.size() >> 8);
      out.write(members.size());
      for (User user : members) {
        byte[] memberBytes = user.getBytes();
        out.write(memberBytes.length >> 8);
        out.write(memberBytes.length);
        out.write(memberBytes);
      }

      return out.toByteArray();
    }
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
