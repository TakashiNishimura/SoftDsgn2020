package com.example.wsp_spring.old;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 円をアルゼンチンペソに両替します
 * http://localhost:8080/demo
 */
@Controller
public class Demo {

  @GetMapping("/demo")
  @ResponseBody
  public String doGet() {
    return """
        <form action='demo' method='POST'>
          <input type='text' name='yen' />円
          <button type='submit'>両替</button>
        </form>
        """;
  }

  @PostMapping("/demo")
  @ResponseBody
  public String doPost(int yen) {
    float ars = yen * 0.7290F;  //円をアルゼンチンペソに変換
    return String.format("<p>%d 円は %.2f アルゼンチンペソ</p>", yen, ars);
  }
}
