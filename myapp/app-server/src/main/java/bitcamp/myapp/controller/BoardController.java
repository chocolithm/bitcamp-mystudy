package bitcamp.myapp.controller;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.User;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@Controller
public class BoardController {

  private BoardService boardService;
  private String uploadDir;

  public BoardController(BoardService boardService, ServletContext ctx) {
    this.boardService = boardService;
    this.uploadDir = ctx.getRealPath("/upload/board");
  }

  @GetMapping("/board/form")
  public void form() {
  }

  @PostMapping("/board/add")
  public String add(
      Board board,
      MultipartFile[] files,
      HttpSession session) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인 하지 않았습니다.");
    }

    board.setWriter(loginUser);

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

    for (MultipartFile file : files) {
      if (file.getSize() == 0) {
        continue;
      }

      AttachedFile attachedFile = new AttachedFile();
      attachedFile.setFilename(UUID.randomUUID().toString());
      attachedFile.setOriginFilename(file.getOriginalFilename());

      file.transferTo(new File(this.uploadDir + "/" + attachedFile.getFilename()));

      attachedFiles.add(attachedFile);
    }

    board.setAttachedFiles(attachedFiles);

    boardService.add(board);
    return "redirect:list";
  }

  @GetMapping("/board/list")
  public void list(Model model) throws Exception {
    List<Board> list = boardService.list();
    model.addAttribute("list", list);
  }

  @GetMapping("/board/view")
  public void view(int no, Model model) throws Exception {
    Board board = boardService.get(no);
    if (board == null) {
      throw new Exception("게시글이 존재하지 않습니다.");
    }

    boardService.increaseViewCount(board.getNo());
    model.addAttribute("board", board);
  }

  @PostMapping("/board/update")
  public String update(
      int no,
      String title,
      String content,
      Part[] files,
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
    for (Part part : files) {
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

  @GetMapping("/board/delete")
  public String delete(
      int no,
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

  @GetMapping("/board/file/delete")
  public String fileDelete(int fileNo, HttpSession session) throws Exception {

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
