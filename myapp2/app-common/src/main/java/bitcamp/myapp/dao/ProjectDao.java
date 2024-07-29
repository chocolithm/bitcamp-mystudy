package bitcamp.myapp.dao;

import bitcamp.myapp.vo.Project;
import java.util.List;

public interface ProjectDao {
  boolean insert(Project project) throws Exception;

  Project findBy(int no) throws Exception;

  List<Project> list() throws Exception;

  boolean update(Project project) throws Exception;

  boolean delete(Project project) throws Exception;
}
