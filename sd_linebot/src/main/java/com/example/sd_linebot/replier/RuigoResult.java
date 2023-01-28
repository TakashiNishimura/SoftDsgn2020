package com.example.sd_linebot.replier;

import com.example.sd_linebot.data.Words;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class RuigoResult implements Replier {

  private MessageEvent<TextMessageContent> event;

  public RuigoResult(MessageEvent<TextMessageContent> event) {
    this.event = event;
  }

  @Override
  public Message reply() {
    var tmc = this.event.getMessage();
    String word = tmc.getText();

    var restTemplate = new RestTemplate();
    String url = "http://localhost:5000/api/" + word;
    var words = restTemplate.getForObject(url, Words.class);

    String displayingText = "";
    if (Objects.nonNull(words)) {
      for (String w : words.getWords()) {
        displayingText += w + ", ";
      }
    }

    return reply(String.format("%sの類語は：%s", word, displayingText));
  }
}
