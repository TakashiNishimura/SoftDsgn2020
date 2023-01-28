package com.example.wsp_spring.old;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 足し算をします
 * Spring Web-MVCです
 * http://localhost:8080/sum
 */
@Controller
public class Sum {

  @GetMapping("/sum")
  @ResponseBody
  public String doGet() {
    return """
        <form action='sum' method='POST'>
          <input type='text' name='x' />＋
          <input type='text' name='y' />
          <button type='submit'>加算</button>
        </form>
        """;
  }

  @PostMapping("/sum")
  @ResponseBody
  public String doPost(int x, int y) {
    return String.format("<p>%d + %d = %d</p>", x, y, x + y);
  }

}
