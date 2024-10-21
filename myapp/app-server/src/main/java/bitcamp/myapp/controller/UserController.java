package bitcamp.myapp.controller;

import bitcamp.myapp.service.StorageService;
import bitcamp.myapp.service.UserService;
import bitcamp.myapp.vo.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService userService;
  private final StorageService storageService;

  private String folderName = "user/";

  @GetMapping("form")
  public String form() {
    return "user/form";
  }

  @PostMapping
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
    return "redirect:../users";
  }

  @GetMapping
  public String list(Model model) throws Exception {
    List<User> list = userService.list();
    model.addAttribute("list", list);
    return "user/list";
  }

  @GetMapping("{no}")
  public String view(
      @PathVariable int no,
      Model model) throws Exception {
    User user = userService.get(no);
    model.addAttribute("user", user);
    return "user/view";
  }

  @PostMapping("{no}")
  public String update(
      @PathVariable int no,
      User user,
      MultipartFile file) throws Exception {

    user.setNo(no);
    User oldUser = userService.get(no);

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
      return "redirect:../users";
    } else {
      throw new Exception("없는 회원입니다.");
    }
  }

  @Transactional
  @DeleteMapping({"no"})
  @ResponseBody
  public String delete(
      @PathVariable int no) throws Exception {
    User user = userService.get(no);

    if (userService.delete(no)) {
      storageService.delete(folderName + user.getPhoto());
      return "success";
    } else {
      return "failure";
    }
  }
}
