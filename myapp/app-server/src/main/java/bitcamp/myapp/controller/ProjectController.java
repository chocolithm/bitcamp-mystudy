package bitcamp.myapp.controller;

import bitcamp.myapp.annotation.Controller;
import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.annotation.RequestParam;
import bitcamp.myapp.service.ProjectService;
import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

@Controller
public class ProjectController {

  private UserService userService;
  private ProjectService projectService;

  public ProjectController(UserService userService, ProjectService projectService) {
    this.userService = userService;
    this.projectService = projectService;
  }

  @RequestMapping("/project/form1")
  public String form1() throws Exception {
    return "/project/form1.jsp";
  }

  @RequestMapping("/project/form2")
  public String form2(Project project, Map<String, Object> map, HttpSession session) throws Exception {
    session.setAttribute("project", project);

    List<User> users = userService.list();
    map.put("users", users);

    return "/project/form2.jsp";
  }

  @RequestMapping("/project/form3")
  public String form3(
      @RequestParam("member") int[] memberNos,
      HttpSession session) throws Exception {
    Project project = (Project) session.getAttribute("project");
    if (memberNos.length > 0) {
      List<User> members = new ArrayList<>();
      for (int memberNo : memberNos) {
        User user = userService.get(memberNo);
        members.add(user);
      }
      project.setMembers(members);
    }

    return "/project/form3.jsp";
  }

  @RequestMapping("/project/add")
  public String add(HttpSession session) throws Exception {
    Project project = (Project) session.getAttribute("project");
    projectService.add(project);
    session.removeAttribute("project");
    return "redirect:list";
  }

  @RequestMapping("/project/list")
  public String list(Map<String, Object> map) throws Exception {
    List<Project> list = projectService.list();
    map.put("list", list);
    return "/project/list.jsp";
  }

  @RequestMapping("/project/view")
  public String view(@RequestParam("no") int no, Map<String, Object> map) throws Exception {
    Project project = projectService.get(no);
    List<User> users = userService.list();

    map.put("project", project);
    map.put("users", users);
    return "/project/view.jsp";
  }

  @RequestMapping("/project/update")
  public String update(Project project, @RequestParam("member") int[] memberNos) throws Exception {
    if (memberNos.length > 0) {
      List<User> members = new ArrayList<>();
      for (int memberNo : memberNos) {
        members.add(new User(memberNo));
      }
      project.setMembers(members);
    }

    if (!projectService.update(project)) {
      throw new Exception("없는 프로젝트입니다!");
    }
    return "redirect:list";
  }

  @RequestMapping("/project/delete")
  public String delete(@RequestParam("no") int no) throws Exception {
    if (projectService.delete(no)) {
      return "redirect:list";
    } else {
      throw new Exception("없는 프로젝트입니다.");
    }
  }
}
