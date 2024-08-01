package bitcamp.myapp.dao.skel;

import static bitcamp.net.ResponseStatus.ERROR;
import static bitcamp.net.ResponseStatus.FAILURE;
import static bitcamp.net.ResponseStatus.SUCCESS;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ProjectDaoSkel {

  private ProjectDao projectDao;

  public ProjectDaoSkel(ProjectDao projectDao) {
    this.projectDao = projectDao;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    String command = in.readUTF();

    Project project = null;
    int no = 0;

    switch (command) {
      case "insert":
        project = (Project) in.readObject();
        projectDao.insert(project);
        out.writeUTF(SUCCESS);
        break;
      case "list":
        List<Project> list = projectDao.list();
        out.writeUTF(SUCCESS);
        out.writeObject(list);
        break;
      case "get":
        no = in.readInt();
        project = projectDao.findBy(no);
        if (project != null) {
          out.writeUTF(SUCCESS);
          out.writeObject(project);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "update":
        project = (Project) in.readObject();
        if (projectDao.update(project)) {
          out.writeUTF(SUCCESS);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "delete":
        no = in.readInt();
        if (projectDao.delete(no)) {
          out.writeUTF(SUCCESS);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      default:
        out.writeUTF(ERROR);
        out.writeUTF("무효한 명령입니다.");
    }

    out.flush();
  }

}
