package com.example.sfds_linebot.replier;

import com.linecorp.bot.model.message.Message;

import java.time.LocalTime;

public class Greet implements Replier {

  @Override
  public Message reply() {
    if (LocalTime.now().getHour() >= 17) {
      return reply("こんばんは！");
    }
    if (LocalTime.now().getHour() >= 11) {
      return reply("こんにちは！");
    }
    return reply("おはよう！");
  }

}
