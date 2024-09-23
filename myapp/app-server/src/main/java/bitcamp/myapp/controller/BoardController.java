package bitcamp.myapp.controller;

import bitcamp.myapp.annotation.Controller;
import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.annotation.RequestParam;
import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(
    maxFileSize = 1024 * 1024 * 60,
    maxRequestSize = 1024 * 1024 * 100)
@Controller
public class BoardController {

  private BoardService boardService;
  private String uploadDir;

  public BoardController(BoardService boardService, ServletContext ctx) {
    this.boardService = boardService;
    this.uploadDir = ctx.getRealPath("/upload/board");
  }

  @RequestMapping("/board/form")
  public String form() throws Exception {
    return "/board/form.jsp";
  }

  @RequestMapping("/board/add")
  public String add(
      Board board,
      @RequestParam("files") Part[] parts,
      HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인 하지 않았습니다.");
    }

    board.setWriter(loginUser);

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

    for (Part part : parts) {
      if (part.getSize() == 0) {
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

  @RequestMapping("/board/list")
  public String list(Map<String, Object> map) throws Exception {
    List<Board> list = boardService.list();
    map.put("list", list);
    return "/board/list.jsp";
  }

  @RequestMapping("/board/view")
  public String view(@RequestParam("no") int no, Map<String, Object> map) throws Exception {
    Board board = boardService.get(no);
    if (board == null) {
      throw new Exception("게시글이 존재하지 않습니다.");
    }

    boardService.increaseViewCount(board.getNo());
    map.put("board", board);
    return "/board/view.jsp";
  }

  @RequestMapping("/board/update")
  public String update(
      @RequestParam("no") int no,
      @RequestParam("title") String title,
      @RequestParam("content") String content,
      @RequestParam("files") Part[] parts,
      HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");

    Board board = boardService.get(no);

    if (board == null) {
      throw new Exception("없는 게시글입니다.");
    } else if (loginUser == null || (loginUser.getNo() > 10 && !loginUser.equals(board.getWriter()))) {
      throw new Exception("변경 권한이 없습니다.");
    }

    board.setTitle(title);
    board.setContent(content);

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
    for (Part part : parts) {
      if (part.getSize() == 0) {
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
  public String delete(
      @RequestParam("no") int no,
      HttpSession session) throws Exception {
    User loginUser = (User) session.getAttribute("loginUser");

    Board board = boardService.get(no);

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

    boardService.delete(no);
    return "redirect:list";
  }

  @RequestMapping("/board/file/delete")
  public String fileDelete(
      @RequestParam("fileNo") int fileNo,
      HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인하지 않았습니다.");
    }

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
