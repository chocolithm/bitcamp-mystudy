package bitcamp.myapp.controller;

import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.service.ProjectService;
import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProjectController {

  private UserService userService;
  private ProjectService projectService;

  public ProjectController(UserService userService, ProjectService projectService) {
    this.userService = userService;
    this.projectService = projectService;
  }

  @RequestMapping("/project/form1")
  public String form1(HttpServletRequest req, HttpServletResponse res) throws Exception {
    return "/project/form1.jsp";
  }

  @RequestMapping("/project/form2")
  public String form2(HttpServletRequest req, HttpServletResponse res) throws Exception {
    Project project = new Project();
    project.setTitle(req.getParameter("title"));
    project.setDescription(req.getParameter("description"));
    project.setStartDate(Date.valueOf(req.getParameter("startDate")));
    project.setEndDate(Date.valueOf(req.getParameter("endDate")));

    HttpSession session = req.getSession();
    session.setAttribute("project", project);

    List<User> users = userService.list();
    req.setAttribute("users", users);

    return "/project/form2.jsp";
  }

  @RequestMapping("/project/form3")
  public String form3(HttpServletRequest req, HttpServletResponse res) throws Exception {
    Project project = (Project) req.getSession().getAttribute("project");

    String[] memberNos = req.getParameterValues("member");
    if (memberNos != null) {
      List<User> members = new ArrayList<>();
      for (String memberNo : memberNos) {
        User user = userService.get(Integer.parseInt(memberNo));
        members.add(user);
      }
      project.setMembers(members);
    }

    return "/project/form3.jsp";
  }

  @RequestMapping("/project/add")
  public String add(HttpServletRequest req, HttpServletResponse res) throws Exception {
    Project project = (Project) req.getSession().getAttribute("project");

    projectService.add(project);
    req.getSession().removeAttribute("project");
    return "redirect:list";
  }

  @RequestMapping("/project/list")
  public String list(HttpServletRequest req, HttpServletResponse res) throws Exception {
    List<Project> list = projectService.list();
    req.setAttribute("list", list);
    return "/project/list.jsp";
  }

  @RequestMapping("/project/view")
  public String view(HttpServletRequest req, HttpServletResponse res) throws Exception {
    int projectNo = Integer.parseInt(req.getParameter("no"));
    Project project = projectService.get(projectNo);
    List<User> users = userService.list();

    req.setAttribute("project", project);
    req.setAttribute("users", users);
    return "/project/view.jsp";
  }

  @RequestMapping("/project/update")
  public String update(HttpServletRequest req, HttpServletResponse res) throws Exception {
    Project project = new Project();
    project.setNo(Integer.parseInt(req.getParameter("no")));
    project.setTitle(req.getParameter("title"));
    project.setDescription(req.getParameter("description"));
    project.setStartDate(Date.valueOf(req.getParameter("startDate")));
    project.setEndDate(Date.valueOf(req.getParameter("endDate")));

    String[] memberNos = req.getParameterValues("member");
    if (memberNos != null) {
      List<User> members = new ArrayList<>();
      for (String memberNo : memberNos) {
        members.add(new User(Integer.parseInt(memberNo)));
      }
      project.setMembers(members);
    }

    if (!projectService.update(project)) {
      throw new Exception("없는 프로젝트입니다!");
    }
    return "redirect:list";
  }

  @RequestMapping("/project/delete")
  public String delete(HttpServletRequest req, HttpServletResponse res) throws Exception {
    int projectNo = Integer.parseInt(req.getParameter("no"));

    if (projectService.delete(projectNo)) {
      return "redirect:list";
    } else {
      throw new Exception("없는 프로젝트입니다.");
    }
  }
}
