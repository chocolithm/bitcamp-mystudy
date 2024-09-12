package bitcamp.myapp.servlet;

import bitcamp.myapp.service.BoardService;
import bitcamp.myapp.vo.AttachedFile;
import bitcamp.myapp.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

  private BoardService boardService;
  private Map<String, String> downloadPathMap = new HashMap<>();

  @Override
  public void init() throws ServletException {
    this.boardService = (BoardService) this.getServletContext().getAttribute("boardService");
    this.downloadPathMap.put("board", this.getServletContext().getRealPath("/upload/board"));
    this.downloadPathMap.put("user", this.getServletContext().getRealPath("/upload/user"));
    this.downloadPathMap.put("project", this.getServletContext().getRealPath("/upload/project"));
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      User loginUser = (User) ((HttpServletRequest) req).getSession().getAttribute("loginUser");
      if (loginUser == null) {
        throw new Exception("로그인 하지 않았습니다.");
      }

      String path = req.getParameter("path");
      String downloadDir = downloadPathMap.get(path);

      if (path.equals("board")) {
        int fileNo = Integer.parseInt(req.getParameter("fileNo"));
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
    } catch (Exception e) {
      req.setAttribute("exception", e);
    }
  }

}
