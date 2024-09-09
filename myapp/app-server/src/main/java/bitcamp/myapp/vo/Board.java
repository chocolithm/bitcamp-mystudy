package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Board implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String content;
  private User writer;
  private Date createdDate;
  private int viewCount;
  private List<AttachedFile> attachedFiles;

  public Board() {

  }

  public Board(int no) {
    this.no = no;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Board board = (Board) o;
    return no == board.no;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(no);
  }

  @Override
  public String toString() {
    return "Board{" +
        "no=" + no +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", writer=" + writer +
        ", createdDate=" + createdDate +
        ", viewCount=" + viewCount +
        '}';
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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getWriter() {
    return writer;
  }

  public void setWriter(User writer) {
    this.writer = writer;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public List<AttachedFile> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(List<AttachedFile> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }
}
