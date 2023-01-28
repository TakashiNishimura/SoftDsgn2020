package com.example.sfds_linebot.replier;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;

public class Parrot implements Replier {

  private MessageEvent<TextMessageContent> event;

  public Parrot(MessageEvent<TextMessageContent> event) {
    this.event = event;
  }

  @Override
  public Message reply() {
    return reply(this.event.getMessage().getText());
  }

}
