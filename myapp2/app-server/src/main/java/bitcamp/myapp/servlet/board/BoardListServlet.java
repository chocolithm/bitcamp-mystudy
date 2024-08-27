package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/board/list")
public class BoardListServlet implements Servlet {

  private ServletConfig config;
  private BoardDao boardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    this.config = config;

    boardDao = (BoardDao) config.getServletContext().getAttribute("boardDao");
  }

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");

    PrintWriter out = res.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("    <meta charset='UTF-8'>");
    out.println("    <title>Title</title>");
    out.println("</head>");
    out.println("<body>");

    try {

      out.println("<h1>게시글 목록</h1>");
      out.println("<table border='1'>");
      out.println("  <thead>");
      out.println("      <tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th></tr>");
      out.println("  </thead>");
      out.println("  <tbody>");

      for (Board board : boardDao.list()) {
        out.printf("      <tr><td>%d</td><td><a href='/board/view?no=%1$d'>%s</a></td><td>%s</td><td>%tY-%4$tm-%4$td</td><td>%d</td></tr>\n",
                board.getNo(),
                board.getTitle(),
                board.getWriter().getName(),
                board.getCreatedDate(),
                board.getViewCount());
      }

      out.println("  </tbody>");
      out.println("</table>");

    } catch (Exception e) {
      out.println("<p>목록 조회 중 오류 발생!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }

  @Override
  public void destroy() {
  }

  @Override
  public String getServletInfo() {
    return "게시글 목록 조회";
  }

  @Override
  public ServletConfig getServletConfig() {
    return this.config;
  }
}
