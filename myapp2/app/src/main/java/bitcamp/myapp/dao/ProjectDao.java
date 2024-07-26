package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Project;
import java.util.List;

public interface ProjectDao {
  boolean insert(Project project);

  Project findBy(int no);

  List<Project> list();

  boolean update(Project project);

  boolean delete(Project project);
}
