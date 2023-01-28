package com.example.sfds_linebot.data;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserModeManager {

  private final Map<String, Mode> userModeMap;

  public UserModeManager() {
    this.userModeMap = new HashMap<>();
  }

  public void toRuigoMode(String lineUserId) {
    userModeMap.put(lineUserId, Mode.RUIGO);
  }

  public void toDefaultMode(String lineUserID) {
    userModeMap.put(lineUserID, Mode.DEFAULT);
  }

  public Mode getMode(String lineUserId) {
    var mode = userModeMap.get(lineUserId);
    if (Objects.isNull(mode)) {
      toDefaultMode(lineUserId);
      mode = getMode(lineUserId);
    }
    return mode;
  }

}
