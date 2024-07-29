package bitcamp.myapp.dao.skel;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import static bitcamp.net.ResponseStatus.ERROR;
import static bitcamp.net.ResponseStatus.FAILURE;
import static bitcamp.net.ResponseStatus.SUCCESS;

public class UserDaoSkel {

  private UserDao userDao;

  public UserDaoSkel(UserDao userDao) {
    this.userDao = userDao;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    String command = in.readUTF();
    User user = null;
    int no = 0;

    switch (command) {
      case "insert":
        user = (User) in.readObject();
        if (userDao.insert(user)) {
          out.writeUTF(SUCCESS);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "get":
        no = in.readInt();
        user = userDao.findBy(no);
        if (user != null) {
          out.writeUTF(SUCCESS);
          out.writeObject(user);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "list":
        List<User> list = userDao.list();
        if (list != null) {
          out.writeUTF(SUCCESS);
          out.writeObject(list);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "update":
        user = (User) in.readObject();
        if (userDao.update(user)) {
          out.writeUTF(SUCCESS);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "delete":
        user = (User) in.readObject();
        if (userDao.delete(user)) {
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
