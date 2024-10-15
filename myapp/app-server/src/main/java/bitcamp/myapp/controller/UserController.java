package bitcamp.myapp.controller;

import bitcamp.myapp.service.StorageService;
import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {

  private UserService userService;
  private StorageService storageService;

  private String folderName = "user/";

  public UserController(UserService userService, StorageService storageService) {
    this.userService = userService;
    this.storageService = storageService;
  }

  @GetMapping("form")
  public String form() {
    return "user/form";
  }

  @PostMapping("add")
  public String add(User user, MultipartFile file) throws Exception {
    String fileName = UUID.randomUUID().toString();

    Map<String, Object> options = new HashMap<>();
    options.put(StorageService.CONTENT_TYPE, file.getContentType());

    storageService.upload(
        folderName + fileName,
        file.getInputStream(),
        options);

    user.setPhoto(fileName);
    userService.add(user);
    return "redirect:list";
  }

  @GetMapping("list")
  public String list(Model model) throws Exception {
    List<User> list = userService.list();
    model.addAttribute("list", list);
    return "user/list";
  }

  @GetMapping("view")
  public String view(int no, Model model) throws Exception {
    User user = userService.get(no);
    model.addAttribute("user", user);
    return "user/view";
  }

  @PostMapping("update")
  public String update(User user, MultipartFile file) throws Exception {
    User oldUser = userService.get(user.getNo());

    if (file != null && file.getSize() > 0) {
      storageService.delete(folderName + oldUser.getPhoto());

      String fileName = UUID.randomUUID().toString();
      Map<String, Object> options = new HashMap<>();
      options.put(StorageService.CONTENT_TYPE, file.getContentType());

      storageService.upload(
          folderName + fileName,
          file.getInputStream(),
          options);

      user.setPhoto(fileName);
    } else {
      user.setPhoto(oldUser.getPhoto());
    }

    if (userService.update(user)) {
      return "redirect:list";
    } else {
      throw new Exception("없는 회원입니다.");
    }
  }

  @Transactional
  @GetMapping("delete")
  public String delete(int no) throws Exception {
    User user = userService.get(no);

    if (userService.delete(no)) {
      storageService.delete(folderName + user.getPhoto());
      return "redirect:list";
    } else {
      throw new Exception("없는 회원입니다.");
    }
  }
}
