package com.example.sfds_linebot.web;

import com.example.sfds_linebot.data.Words;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;


@Controller
public class RuigoFormController {

  @GetMapping("/ruigo")
  public String getRuigo() {
    return "ruigo_form";
  }

  @PostMapping("/ruigo")
  public String postRuigo(String tango, Model model) {
    var words = new RestTemplate().getForObject("http://localhost:5000/api/" + tango, Words.class);

    String displayingText = "";
    if (Objects.nonNull(words)) {
      for (String w : words.getWords()) {
        displayingText += w + ", ";
      }
      model.addAttribute("tango", tango);
      model.addAttribute("ruigo", displayingText);
      return "ruigo_result";
    }
    model.addAttribute("tango", tango);
    model.addAttribute("ruigo", "見つかりませんでした");
    return "ruigo_result";
  }

}
