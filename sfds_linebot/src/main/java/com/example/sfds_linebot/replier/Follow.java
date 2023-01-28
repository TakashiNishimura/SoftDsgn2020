package com.example.sfds_linebot.replier;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.message.Message;

public class Follow implements Replier {

  private FollowEvent event;

  public Follow(FollowEvent event) {
    this.event = event;
  }

  @Override
  public Message reply() {
    return reply("あなたのユーザIDは " + this.event.getSource().getUserId());
  }

}
