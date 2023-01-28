package com.example.sfds_linebot.replier;

import com.linecorp.bot.model.message.Message;

import java.util.Random;

public class Omikuji implements Replier {

  @Override
  public Message reply() {
    return switch (new Random().nextInt(3)) {
      case 2 -> replyImage("https://3.bp.blogspot.com/-vQSPQf-ytsc/T3K7QM3qaQI/AAAAAAAAE-s/6SB2q7ltxwg/s1600/omikuji_daikichi.png");
      case 1 -> replyImage("https://2.bp.blogspot.com/-27IG0CNV-ZE/VKYfn_1-ycI/AAAAAAAAqXw/fr6Y72lOP9s/s400/omikuji_kichi.png");
      default -> replyImage("https://4.bp.blogspot.com/-qCfF4H7YOvE/T3K7R5ZjQVI/AAAAAAAAE-4/Hd1u2tzMG3Q/s1600/omikuji_kyou.png");
    };
  }

}
