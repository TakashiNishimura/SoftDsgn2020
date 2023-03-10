package WS01;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * アクセス回数が表示されます
 */
public class SimpleHandler extends MyHandler {

  private int count;

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    super.handle(exchange);

    String response = ++count + "回目のアクセスです！";
    Headers headers = exchange.getResponseHeaders();
    headers.add("Content-Type", "text/plain; charset=UTF-8");
    exchange.sendResponseHeaders(200, response.getBytes().length);

    try (OutputStream os = exchange.getResponseBody()) {
      os.write(response.getBytes());
    }
  }

}
