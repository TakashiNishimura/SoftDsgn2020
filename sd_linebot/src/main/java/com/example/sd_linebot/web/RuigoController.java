package com.example.sd_linebot.web;

import com.example.sd_linebot.data.Words;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Controller
public class RuigoController {

  private final String urlApi = "http://localhost:5000/api/";

  @GetMapping("/ruigo")
  public String getRuigo() {
    return "ruigo_form";
  }

  @GetMapping("/ruigo/{tango}")
  public String getRuigoWord(@PathVariable("tango") String tango, Model model) {
    Words words = null;
    try {
      words = new RestTemplate().getForObject(urlApi + tango, Words.class);
    } catch (Exception e) {
    }

    String displayingText = "";
    if (Objects.nonNull(words)) {
      model.addAttribute("tango", tango);
      model.addAttribute("ruigo", words.getWords());
//      model.addAttribute("ruigourl", Collections.singletonList("見つかりませんでした"));
      return "ruigo_result";
    }
    model.addAttribute("tango", tango);
    model.addAttribute("ruigo", Collections.singletonList("見つかりませんでした"));
//    model.addAttribute("ruigourl", "見つかりませんでした");
    return "ruigo_result";
  }

  @PostMapping("/ruigo")
  public String postRuigo(String tango, Model model) {
    Words words = null;
    try {
      words = new RestTemplate().getForObject(urlApi + tango, Words.class);
    } catch (Exception e) {
    }

    String displayingText = "";
    if (Objects.nonNull(words)) {
      model.addAttribute("tango", tango);
      model.addAttribute("ruigo", words.getWords());
//      model.addAttribute("ruigourl", Collections.singletonList("見つかりませんでした"));
      return "ruigo_result";
    }
    model.addAttribute("tango", tango);
    model.addAttribute("ruigo", Collections.singletonList("見つかりませんでした"));
//    model.addAttribute("ruigourl", "見つかりませんでした");
    return "ruigo_result";
  }

}
