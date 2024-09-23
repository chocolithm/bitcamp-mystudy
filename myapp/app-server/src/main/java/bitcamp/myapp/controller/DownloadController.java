package bitcamp.myapp.controller;

import bitcamp.myapp.annotation.Controller;
import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.annotation.RequestParam;
import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.User;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class DownloadController {

  private BoardService boardService;
  private Map<String, String> downloadPathMap = new HashMap<>();

  public DownloadController(BoardService boardService, ServletContext ctx) {
    this.boardService = boardService;
    this.downloadPathMap.put("board", ctx.getRealPath("/upload/board"));
    this.downloadPathMap.put("user", ctx.getRealPath("/upload/user"));
    this.downloadPathMap.put("project", ctx.getRealPath("/upload/project"));
  }

  @RequestMapping("/download")
  public void download(
      @RequestParam("path") String path,
      @RequestParam("fileNo") int fileNo,
      HttpSession session,
      HttpServletResponse res) throws Exception {

    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      throw new Exception("로그인 하지 않았습니다.");
    }

    String downloadDir = downloadPathMap.get(path);

    if (path.equals("board")) {
      AttachedFile attachedFile = boardService.getAttachedFile(fileNo);

      res.setHeader(
          "Content-Disposition",
          String.format("attachment; filename=\"%s\"", attachedFile.getOriginFilename())
      );

      BufferedInputStream downloadFileIn = new BufferedInputStream(
          new FileInputStream(downloadDir + "/" + attachedFile.getFilename()));
      OutputStream out = res.getOutputStream();

      int b;
      while ((b = downloadFileIn.read()) != -1) {
        out.write(b);
      }

      downloadFileIn.close();


    } else if (path.equals("user")) {

    } else {

    }
  }
}
