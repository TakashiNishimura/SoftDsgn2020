package com.example.wsp_spring.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * アカウント系のServiceです.
 */
@Service
public class SignService {

  private final DBRepository dbRepository;
  private final HttpSession session;

  @Autowired
  public SignService(DBRepository dbRepository, HttpSession session) {
    this.dbRepository = dbRepository;
    this.session = session;
  }

  public UserValue signIn(String userId, String userPassword) {
    var authnData = dbRepository.findAuthnData(userId, userPassword);
    if (Objects.isNull(authnData)) {
      return null;
    }

    System.out.print(authnData.getUserId() + ", " + authnData.getUserName());
    System.out.println(" --- sessionId: " + session.getId());

    var userValue = new UserValue(authnData.getUserId(), authnData.getUserName());
    this.sign(userValue);

    return userValue;
  }

  /**
   * サインアップを行います.
   * 引数のすべての値をデータベースに登録します.
   *
   * @param userId       String ユーザid
   * @param userPassword String パスワード
   * @param userName     String ユーザネーム
   * @return データベースの更新があったときtrue
   */
  public boolean signUp(String userId, String userPassword, String userName) {
    return 0 < dbRepository.insertAuthnData(new AuthnData(userId, userPassword, userName));
  }

  private void sign(UserValue userValue) {
    session.setAttribute("signedUserValue", userValue);
  }

  public UserValue whoIsSigned() {
    var signedUser = (UserValue) session.getAttribute("signedUserValue");
    if (Objects.isNull(signedUser)) {
      throw new RuntimeException("このブラウザはユーザ認証されていません");
    }
    return signedUser;
  }

  public void signOut() {
    session.invalidate();
  }

}
