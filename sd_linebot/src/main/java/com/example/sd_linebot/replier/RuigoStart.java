package com.example.sd_linebot.replier;

import com.linecorp.bot.model.message.Message;

public class RuigoStart implements Replier {

  @Override
  public Message reply() {
    return reply("検索したい語を入力してください");
  }

}
