package bitcamp.myapp.dao.stub;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import static bitcamp.net.ResponseStatus.SUCCESS;

public class UserDaoStub implements UserDao {

  private ObjectInputStream in;
  private ObjectOutputStream out;
  private String dataName;

  public UserDaoStub(ObjectInputStream in, ObjectOutputStream out, String dataName)
      throws Exception {
    this.in = in;
    this.out = out;
    this.dataName = dataName;
  }

  @Override
  public boolean insert(User user) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("insert");
    out.writeObject(user);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return true;
    }

    return false;
  }

  @Override
  public List<User> list() throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("list");
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return (List<User>) in.readObject();
    }
    return null;
  }

  @Override
  public User findBy(int no) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("get");
    out.writeInt(no);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return (User) in.readObject();
    }
    return null;
  }

  @Override
  public boolean update(User user) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("update");
    out.writeObject(user);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return true;
    }
    return false;
  }

  @Override
  public boolean delete(int no) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("delete");
    out.writeInt(no);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return true;
    }
    return false;
  }
}
