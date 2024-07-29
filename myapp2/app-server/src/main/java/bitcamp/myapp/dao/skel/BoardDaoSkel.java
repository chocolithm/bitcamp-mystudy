package bitcamp.myapp.dao.skel;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import static bitcamp.net.ResponseStatus.ERROR;
import static bitcamp.net.ResponseStatus.FAILURE;
import static bitcamp.net.ResponseStatus.SUCCESS;

public class BoardDaoSkel {
  private BoardDao boardDao;

  public BoardDaoSkel(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    String command = in.readUTF();
    Board board = null;
    int no = 0;

    switch (command) {
      case "insert":
        board = (Board) in.readObject();
        if (boardDao.insert(board)) {
          out.writeUTF(SUCCESS);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "get":
        no = in.readInt();
        board = boardDao.findBy(no);
        if (board != null) {
          out.writeUTF(SUCCESS);
          out.writeObject(board);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "list":
        List<Board> list = boardDao.list();
        if (list != null) {
          out.writeUTF(SUCCESS);
          out.writeObject(list);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "update":
        board = (Board) in.readObject();
        if (boardDao.update(board)) {
          out.writeUTF(SUCCESS);
        } else {
          out.writeUTF(FAILURE);
        }
        break;
      case "delete":
        board = (Board) in.readObject();
        if (boardDao.delete(board)) {
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
