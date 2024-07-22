package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Board implements Serializable, SequenceNo {

  private static int seqNo;

  private int no;
  private String title;
  private String content;
  private Date createdDate;
  private int viewCount;

  public Board() {
  }

  public Board(int no) {
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

  public static Board valueOf(String csv) {
    String[] values = csv.split(",");
    Board board = new Board();
    board.setNo(Integer.parseInt(values[0]));
    board.setTitle(values[1]);
    board.setContent(values[2]);
    board.setCreatedDate(new Date(Long.parseLong(values[3])));
    board.setViewCount(Integer.parseInt(values[4]));
    return board;
  }

  public String toCsvString() {
    return new StringBuilder()
        .append(no).append(",")
        .append(title).append(",")
        .append(content).append(",")
        .append(createdDate.getTime()).append(",")
        .append(viewCount)
        .toString();
  }

  @Override
  public String toString() {
    return "Board{" +
        "no=" + no +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", createdDate=" + createdDate +
        ", viewCount=" + viewCount +
        '}';
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
}
