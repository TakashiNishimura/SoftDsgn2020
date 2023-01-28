package com.example.sd_linebot.replier;

import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

import java.net.URI;

public interface Replier {

  Message reply();

  default Message reply(String message) {
    return new TextMessage(message);
  }

  default Message replyImage(String url) {
    var uri = URI.create(url);
    return new ImageMessage(uri, uri);
  }

}
