package WS01;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * 円をドルに両替します
 */
public class DynamicHandler implements HttpHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    Headers headers = exchange.getResponseHeaders();
    String method = exchange.getRequestMethod();

    Res res = switch (method) {
      case "GET" -> this.doGet(exchange);
      case "POST" -> this.doPost(exchange);
      default -> this.doOther(exchange);
    };

    headers.add("Content-Type", res.getContentType());
    exchange.sendResponseHeaders(res.getStatusCode(), res.getBodyLength());
    try (OutputStream outputStream = exchange.getResponseBody()) {
      outputStream.write(res.getBodyBytes());
    }
  }

  private Res doOther(HttpExchange exchange) {
    return new Res(495, "<p>405 要求メソッドは許可されていません</p>");
  }

  private Res doGet(HttpExchange exchange) {
    String body = """
        <form action='dynamic' method='POST'>
          <input type='text' name='yen' />円
          <button type='submit'>両替</button>
        </form>
        """;
    return new Res(200, body);
  }


  private Res doPost(HttpExchange exchange) {
    try (Scanner scanner = new Scanner(exchange.getRequestBody(), "UTF-8")) {
      String scanned = scanner.nextLine();  //リクエストボディ取り出し(yen=xxx)
      String[] split = scanned.split("=");
      int yen = Integer.parseInt(split[1]);
      float usd = yen * 0.0095F;  //円をドルに変換

      String body = String.format("<p>%d 円は %.2f ドル</p>", yen, usd);
      return new Res(200, body);
    } catch (RuntimeException e) {
      e.printStackTrace();
      return new Res(500, ("<p>500 サーバ内部でエラーが生じました</p>"));
    }
  }
}
