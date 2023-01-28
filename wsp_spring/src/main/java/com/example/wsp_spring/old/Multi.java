package com.example.wsp_spring.old;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * 掛け算をします
 * Spring Web-MVC, Thymeleafです
 * http://localhost:8080/multi
 */
@Controller
public class Multi {

  @GetMapping("multi")
  public String doGet() {
    return "old/multi_form";
  }

  @PostMapping("multi")
  public String doPost(int x, int y, Model model) {
    model.addAllAttributes(Map.of("x", x, "y", y, "ans", x * y));
    return "old/multi_ans";
  }

}
