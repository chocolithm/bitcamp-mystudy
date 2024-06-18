package bitcamp.myapp.vo;

public class Board {
  private String title;
  private String content;
  private String date;
  private int views;

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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getViews() {
    return views;
  }

  public void setViews(int views) {
    this.views = views;
  }
}
