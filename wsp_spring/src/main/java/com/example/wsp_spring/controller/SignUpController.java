package com.example.wsp_spring.controller;

import com.example.wsp_spring.model.SignService;
import com.example.wsp_spring.model.UserValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ユーザid, 名前, パスワードを作成します.
 * http://localhost:8080/signUp
 */
@Controller
public class SignUpController {

  private final SignService signService;

  @Autowired
  public SignUpController(SignService signService) {
    this.signService = signService;
  }

  @GetMapping("signUp")
  public String getSignUp() {
    return "view/sign_up_form";
  }

  @PostMapping("signUp")
  public String postSignUp(String userId, String userPassword, String userName, Model model) {
    if (!signService.signUp(userId, userPassword, userName)) {
      model.addAttribute("userId", userId);
      return "view/sign_up_failed";
    }
    model.addAttribute("userValue", new UserValue(userId, userName));
    return "view/sign_up_finished";
  }

}
