package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/project/list")
public class ProjectListServlet implements Servlet {

  private ServletConfig config;
  private ProjectDao projectDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    this.config = config;
    projectDao = (ProjectDao) config.getServletContext().getAttribute("projectDao");
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
      out.println("<h1>프로젝트 목록</h1>");
      out.println("<table border='1'>");
      out.println("  <thead>");
      out.println("      <tr><th>번호</th><th>프로젝트</th><th>기간</th></tr>");
      out.println("  </thead>");
      out.println("  <tbody>");

      for (Project project : projectDao.list()) {
        out.printf("      <tr><td>%d</td><td><a href='/project/view?no=%1$d'>%s</a></td><td>%s ~ %s</td></tr>\n",
                project.getNo(), project.getTitle(), project.getStartDate(), project.getEndDate());
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
    return "프로젝트 목록 조회";
  }

  @Override
  public ServletConfig getServletConfig() {
    return this.config;
  }
}
