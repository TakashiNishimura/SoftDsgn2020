package com.example.wsp_spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザ認証のDataです.
 */

public class AuthnData {
  private String userId;
  private String userPassword;
  private String userName;

  public AuthnData() {

  }

  public AuthnData(String userId, String userPassword, String userName) {
    this.userId = userId;
    this.userPassword = userPassword;
    this.userName = userName;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
