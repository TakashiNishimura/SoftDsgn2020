package WS01;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 今日の運勢が表示されます
 */
public class OmikujiHandler extends MyHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    super.handle(exchange);

    OMIKUJI omikuji = OMIKUJI.getOMIKUJI(new Random().nextInt(3));
    String response = "今日の運勢は" +
        omikuji.getUnsei() +
        "です！";

    Headers headers = exchange.getResponseHeaders();
    headers.add("Content-Type", "text/plain; charset=UTF-8");
    exchange.sendResponseHeaders(200, response.getBytes().length);

    try (OutputStream os = exchange.getResponseBody()) {
      os.write(response.getBytes());
    }
  }

}
