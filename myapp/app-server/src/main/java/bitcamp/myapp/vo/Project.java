package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
public class Project implements Serializable {

  private static final long serialVersionUID = 1L;

  private int no;
  private String title;
  private String description;
  private Date startDate;
  private Date endDate;
  private List<User> members;
}
