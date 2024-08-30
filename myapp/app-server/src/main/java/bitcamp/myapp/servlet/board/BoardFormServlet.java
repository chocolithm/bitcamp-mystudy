package bitcamp.myapp.servlet.board;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/board/form")
public class BoardFormServlet extends GenericServlet {

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");
    req.getRequestDispatcher("/board/form.jsp").include(req, res);

  }
}
