package bitcamp.myapp.controller;

import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
    maxFileSize = 1024 * 1024 * 60,
    maxRequestSize = 1024 * 1024 * 100)
public class BoardController {

  private BoardService boardService;
  private String uploadDir;

  public BoardController(BoardService boardService, ServletContext ctx) {
    this.boardService = boardService;
    this.uploadDir = ctx.getRealPath("/upload/board");
  }

  @RequestMapping("/board/add")
  public String add(HttpServletRequest req, HttpServletResponse res) throws Exception {
    if (req.getMethod().equals("GET")) {
      return "/board/form.jsp";

    } else {
      User loginUser = (User) ((HttpServletRequest) req).getSession().getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인 하지 않았습니다.");
      }

      Board board = new Board();
      board.setWriter(loginUser);
      board.setTitle(req.getParameter("title"));
      board.setContent(req.getParameter("content"));

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      Collection<Part> parts = req.getParts();
      for (Part part : parts) {
        if (!part.getName().equals("files") || part.getSize() == 0) {
          continue;
        }

        AttachedFile attachedFile = new AttachedFile();
        attachedFile.setFilename(UUID.randomUUID().toString());
        attachedFile.setOriginFilename(part.getSubmittedFileName());

        part.write(this.uploadDir + "/" + attachedFile.getFilename());

        attachedFiles.add(attachedFile);
      }

      board.setAttachedFiles(attachedFiles);

      boardService.add(board);
      return "redirect:list";
    }
  }

  @RequestMapping("/board/list")
  public String list(HttpServletRequest req, HttpServletResponse res) throws Exception {
    List<Board> list = boardService.list();
    req.setAttribute("list", list);
    return "/board/list.jsp";
  }

  @RequestMapping("/board/view")
  public String view(HttpServletRequest req, HttpServletResponse res) throws Exception {
    int boardNo = Integer.parseInt(req.getParameter("no"));
    Board board = boardService.get(boardNo);
    if (board == null) {
      throw new Exception("게시글이 존재하지 않습니다.");
    }

    boardService.increaseViewCount(board.getNo());
    req.setAttribute("board", board);
    return "/board/view.jsp";
  }

  @RequestMapping("/board/update")
  public String update(HttpServletRequest req, HttpServletResponse res) throws Exception {
    User loginUser = (User) req.getSession().getAttribute("loginUser");

    int boardNo = Integer.parseInt(req.getParameter("no"));
    Board board = boardService.get(boardNo);

    if (board == null) {
      throw new Exception("없는 게시글입니다.");
    } else if (loginUser == null || (loginUser.getNo() > 10 && !loginUser.equals(board.getWriter()))) {
      throw new Exception("변경 권한이 없습니다.");
    }

    board.setTitle(req.getParameter("title"));
    board.setContent(req.getParameter("content"));

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
    Collection<Part> parts = req.getParts();
    for (Part part : parts) {
      if (!part.getName().equals("files") || part.getSize() == 0) {
        continue;
      }

      AttachedFile attachedFile = new AttachedFile();
      attachedFile.setFilename(UUID.randomUUID().toString());
      attachedFile.setOriginFilename(part.getSubmittedFileName());

      part.write(uploadDir + "/" + attachedFile.getFilename());

      attachedFiles.add(attachedFile);
    }

    board.setAttachedFiles(attachedFiles);

    boardService.update(board);
    return "redirect:list";
  }

  @RequestMapping("/board/delete")
  public String delete(HttpServletRequest req, HttpServletResponse res) throws Exception {
    User loginUser = (User) req.getSession().getAttribute("loginUser");

    int boardNo = Integer.parseInt(req.getParameter("no"));
    Board board = boardService.get(boardNo);

    if (board == null) {
      throw new Exception("없는 게시글입니다.");
    } else if (loginUser == null || (loginUser.getNo() > 10 && !loginUser.equals(board.getWriter()))) {
      throw new Exception("삭제 권한이 없습니다.");
    }

    for (AttachedFile attachedFile : board.getAttachedFiles()) {
      File file = new File(uploadDir + "/" + attachedFile.getFilename());
      if (file.exists()) {
        file.delete();
      }
    }

    boardService.delete(boardNo);
    return "redirect:list";
  }

  @RequestMapping("/board/file/delete")
  public String fileDelete(HttpServletRequest req, HttpServletResponse res) throws Exception {
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
    if (loginUser.getNo() > 10 && !loginUser.equals(board.getWriter())) {
      throw new Exception("삭제 권한이 없습니다.");
    }

    File file = new File(uploadDir + "/" + attachedFile.getFilename());
    if (file.exists()) {
      file.delete();
    }
    boardService.deleteAttachedFile(fileNo);
    return "redirect:../view?no=" + board.getNo();
  }
}
