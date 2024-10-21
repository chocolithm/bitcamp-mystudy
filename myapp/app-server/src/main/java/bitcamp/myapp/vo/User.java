package bitcamp.myapp.vo;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String name;
  private String email;
  private String password;
  private String tel;
  private String photo;

  public User(int no) {
    this.no = no;
  }
}
