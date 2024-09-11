package bitcamp.myapp.service;

import bitcamp.myapp.vo.Project;
import java.util.List;

public interface ProjectService {

  void add(Project project) throws Exception;

  List<Project> list() throws Exception;

  Project get(int projectNo) throws Exception;

  void update(Project project) throws Exception;

  void delete(int projectNo) throws Exception;
}
