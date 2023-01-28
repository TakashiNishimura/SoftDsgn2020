package com.example.wsp_spring.controller;

import com.example.wsp_spring.model.Words;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * http://localhost:8080/api/人
 */
@Controller
public class APIController {

  @GetMapping("api/{word}")
  @ResponseBody
  public String getSimilarNovel(@PathVariable("word") String word) {
    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:5000/api/" + word;
    Words words = restTemplate.getForObject(url, Words.class);
    String displayingText = "";
    if (words != null) {
      for (String w : words.getWords()) {
        displayingText += w + ", ";
      }
    }
    return displayingText;
  }

}