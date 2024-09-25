package bitcamp.myapp.controller;

import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.User;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user/form")
  public String form() {
    return "user/form";
  }

  @PostMapping("/user/add")
  public String add(User user) throws Exception {
    userService.add(user);
    return "redirect:list";
  }

  @GetMapping("/user/list")
  public String list(Model model) throws Exception {
    List<User> list = userService.list();
    model.addAttribute("list", list);
    return "user/list";
  }

  @GetMapping("/user/view")
  public String view(int no, Model model) throws Exception {
    User user = userService.get(no);
    model.addAttribute("user", user);
    return "user/view";
  }

  @PostMapping("/user/update")
  public String update(User user) throws Exception {
    if (userService.update(user)) {
      return "redirect:list";
    } else {
      throw new Exception("없는 회원입니다.");
    }
  }

  @GetMapping("/user/delete")
  public String delete(int no) throws Exception {
    if (userService.delete(no)) {
      return "redirect:list";
    } else {
      throw new Exception("없는 회원입니다.");
    }
  }
}
