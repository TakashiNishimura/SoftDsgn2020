package WS01;

import com.sun.net.httpserver.HttpExchange;

import java.util.Scanner;

/**
 * レスポンスを作成するためのクラスです
 */
public class Res {

  private final int statusCode;
  private final String body;

  public Res(int statusCode, String body) {
    this.statusCode = statusCode;
    this.body = body;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getBody() {
    return body;
  }

  public int getBodyLength() {
    return body.getBytes().length;
  }

  public byte[] getBodyBytes() {
    return body.getBytes();
  }

  public String getContentType() {
    return "text/html; charset=UTF-8";
  }

}
