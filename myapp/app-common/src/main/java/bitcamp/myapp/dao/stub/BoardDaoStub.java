package bitcamp.myapp.dao.stub;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import static bitcamp.net.ResponseStatus.SUCCESS;

public class BoardDaoStub implements BoardDao {

  private ObjectInputStream in;
  private ObjectOutputStream out;
  private String dataName;

  public BoardDaoStub(ObjectInputStream in, ObjectOutputStream out, String dataName)
      throws Exception {
    this.in = in;
    this.out = out;
    this.dataName = dataName;
  }

  @Override
  public boolean insert(Board board) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("insert");
    out.writeObject(board);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return true;
    }

    return false;
  }

  @Override
  public List<Board> list() throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("list");
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return (List<Board>) in.readObject();
    }
    return null;
  }

  @Override
  public Board findBy(int no) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("get");
    out.writeInt(no);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return (Board) in.readObject();
    }
    return null;
  }

  @Override
  public boolean update(Board board) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("update");
    out.writeObject(board);
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
