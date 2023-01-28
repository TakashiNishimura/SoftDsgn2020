package com.example.wsp_spring.controller;

import com.example.wsp_spring.model.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

/**
 * ユーザidとパスワード認証をします.
 * http://localhost:8080/signIn
 */
@Controller
public class SignController {

  private final SignService signService;

  @Autowired
  public SignController(SignService signService) {
    this.signService = signService;
  }

  @GetMapping("signIn")
  public String getSignIn() {
    return "view/sign_in_form";
  }

  @PostMapping("signIn")
  public String postSignIn(String userId, String userPassword, Model model) {
    var userValue = signService.signIn(userId, userPassword);
    if (Objects.isNull(userValue)) {
      return "view/sign_in_failed";
    }
    model.addAttribute("userValue", userValue);
    return "view/signed";
  }

  @GetMapping("/signed")
  public String getSigned(Model model) {
    model.addAttribute("userValue", signService.whoIsSigned());
    return "view/signed";
  }

  @GetMapping("/signOut")
  public String getSignOut() {
    signService.signOut();
    return "view/sign_in_form";
  }

}
