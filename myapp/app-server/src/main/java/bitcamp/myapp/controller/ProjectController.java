package bitcamp.myapp.controller;

import bitcamp.myapp.service.ProjectService;
import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/project")
@SessionAttributes("project") // "project"로 model이나 map에 저장되는 객체가 있으면 session에도 저장하라
public class ProjectController {

  private final UserService userService;
  private final ProjectService projectService;

  @GetMapping("form1")
  public String form1() {
    return "project/form1";
  }

  @PostMapping("form2")
  public ModelAndView form2(Project project) throws Exception {
    List<User> users = userService.list();
    ModelAndView mv = new ModelAndView();

    mv.addObject("users", users);
    mv.addObject("project", project); // @SessionAttributes가 활성화되었기 때문에 세션에 저장됨
    mv.setViewName("project/form2");

    return mv;
  }

  @PostMapping("form3")
  public String form3(
      int[] memberNos,
      @ModelAttribute Project project) throws Exception {
    // session에 "project"로 저장된 객체를 꺼내라

    if (memberNos.length > 0) {
      List<User> members = new ArrayList<>();
      for (int memberNo : memberNos) {
        User user = userService.get(memberNo);
        members.add(user);
      }
      project.setMembers(members);
    }

    return "project/form3";
  }

  @PostMapping("add")
  public String add(@ModelAttribute Project project, SessionStatus sessionStatus) throws Exception {
    projectService.add(project);
    sessionStatus.setComplete(); // @SessionAttributes로 등록된 값을 제거
    return "redirect:list";
  }

  @GetMapping("list")
  public ModelAndView list() throws Exception {
    List<Project> list = projectService.list();
    ModelAndView mv = new ModelAndView();
    mv.addObject("list", list);
    mv.setViewName("project/list");
    return mv;
  }

  @GetMapping("view")
  public ModelAndView view(int no) throws Exception {
    ModelAndView mv = new ModelAndView();

    Project project = projectService.get(no);
    List<User> users = userService.list();

    mv.addObject("project", project);
    mv.addObject("users", users);
    mv.setViewName("project/view");

    return mv;
  }

  @PostMapping("update")
  public String update(Project project, int[] memberNos) throws Exception {
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

  @GetMapping("delete")
  public String delete(int no) throws Exception {
    if (projectService.delete(no)) {
      return "redirect:list";
    } else {
      throw new Exception("없는 프로젝트입니다.");
    }
  }
}
