package com.example.sd_linebot;


import com.example.sd_linebot.data.Mode;
import com.example.sd_linebot.data.UserModeManager;
import com.example.sd_linebot.replier.Follow;
import com.example.sd_linebot.replier.Greet;
import com.example.sd_linebot.replier.Omikuji;
import com.example.sd_linebot.replier.Parrot;
import com.example.sd_linebot.replier.RuigoResult;
import com.example.sd_linebot.replier.RuigoStart;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@LineMessageHandler
public class Callback {

  private static final Logger log = LoggerFactory.getLogger(Callback.class);
  private final UserModeManager userModeManager;

  private LineMessagingClient client;

  @Autowired
  public Callback(LineMessagingClient client, UserModeManager userModeManager) {
    this.client = client;
    this.userModeManager = userModeManager;
  }

  // マッピングされていないEventに対応する
  @EventMapping
  public void handleEvent(Event event) {
    System.out.println("event: " + event);
  }

  // フォローイベントに対応する
  @EventMapping
  public Message handleFollow(FollowEvent event) {
    // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
    return new Follow(event).reply();
  }

  // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
  @EventMapping
  public Message handleMessage(MessageEvent<TextMessageContent> event) {
    var tmc = event.getMessage();
    String text = tmc.getText();
    String lineUserId = event.getSource().getUserId();
    var mode = userModeManager.getMode(lineUserId);

    if (mode.equals(Mode.RUIGO)) {
      userModeManager.toDefaultMode(lineUserId);
      return new RuigoResult(event).reply();
    }

    return switch (text) {
      case "やあ" -> new Greet().reply();
      case "おみくじ" -> new Omikuji().reply();
      case "類語" -> {
        userModeManager.toRuigoMode(lineUserId);
        yield new RuigoStart().reply();
      }
      default -> new Parrot(event).reply();
    };
  }

}
