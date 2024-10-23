package bitcamp.myapp.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import java.beans.PropertyEditorSupport;

@ControllerAdvice
public class GlobalControllerAdvice {

  @InitBinder
  public void initBinder(WebDataBinder webDataBinder) {
    webDataBinder.registerCustomEditor(
            java.util.Date.class,
            new PropertyEditorSupport() {
              @Override
              public void setAsText(String text) throws IllegalArgumentException {
                this.setValue(java.sql.Date.valueOf(text)); // "yyyy-MM-dd" ==> Date 객체
              }
            }
    );
  }

  @ExceptionHandler
  public ModelAndView exceptionHandler(Exception e) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("exception", e);
    mv.setViewName("error");
    return mv;
  }
}
