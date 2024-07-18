package bitcamp.myapp.vo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

public class Board {

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

  public static Board valueOf(byte[] bytes) throws IOException {
    try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
      Board board = new Board();
      byte[] buf = new byte[1000];
      int len;

      board.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

      len = in.read() << 8 | in.read();
      in.read(buf, 0, len);
      board.setTitle(new String(buf, 0, len, StandardCharsets.UTF_8));

      len = in.read() << 8 | in.read();
      in.read(buf, 0, len);
      board.setContent(new String(buf, 0, len, StandardCharsets.UTF_8));

      board.setCreatedDate(
          new Date((long) in.read() << 56 | (long) in.read() << 48 |
              (long) in.read() << 40 | (long) in.read() << 32 |
              (long) in.read() << 24 | (long) in.read() << 16 |
              (long) in.read() << 8 | (long) in.read()
          ));

      board.setViewCount(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

      return board;
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

      bytes = content.getBytes(StandardCharsets.UTF_8);
      out.write(bytes.length >> 8);
      out.write(bytes.length);
      out.write(content.getBytes(StandardCharsets.UTF_8));

      long millis = createdDate.getTime();
      out.write((int) (millis >> 56));
      out.write((int) (millis >> 48));
      out.write((int) (millis >> 40));
      out.write((int) (millis >> 32));
      out.write((int) (millis >> 24));
      out.write((int) (millis >> 16));
      out.write((int) (millis >> 8));
      out.write((int) (millis));

      out.write(viewCount >> 24);
      out.write(viewCount >> 16);
      out.write(viewCount >> 8);
      out.write(viewCount);

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
