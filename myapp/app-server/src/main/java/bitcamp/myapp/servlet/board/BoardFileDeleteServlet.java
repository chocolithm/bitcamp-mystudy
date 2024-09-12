package bitcamp.myapp.servlet.board;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/file/delete")
public class BoardFileDeleteServlet extends HttpServlet {

  private BoardService boardService;
  private String uploadDir;

  @Override
  public void init() throws ServletException {
    boardService = (BoardService) this.getServletContext().getAttribute("boardService");
    uploadDir = this.getServletContext().getRealPath("/upload/board");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      User loginUser = (User) req.getSession().getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인하지 않았습니다.");
      }

      int fileNo = Integer.parseInt(req.getParameter("fileNo"));
      AttachedFile attachedFile = boardService.getAttachedFile(fileNo);
      if (attachedFile == null) {
        throw new Exception("없는 첨부파일입니다.");
      }

      Board board = boardService.get(attachedFile.getBoardNo());
      if (loginUser == null || (loginUser.getNo() > 10 && !loginUser.equals(board.getWriter()))) {
        throw new Exception("삭제 권한이 없습니다.");
      }

      File file = new File(uploadDir + "/" + attachedFile.getFilename());
      if (file.exists()) {
        file.delete();
      }
      boardService.deleteAttachedFile(fileNo);
      req.setAttribute("viewName", "redirect:../view?no=" + board.getNo());

    } catch (Exception e) {
      req.setAttribute("exception", e);
    }
  }
}
