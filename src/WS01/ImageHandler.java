package WS01;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * おみくじの画像が表示されます
 */
public class ImageHandler extends MyHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    super.handle(exchange);

    OMIKUJI omikuji = OMIKUJI.getOMIKUJI(new Random().nextInt(3));
    String response = omikuji.getImg();

    Headers headers = exchange.getResponseHeaders();
    headers.add("Content-Type", "text/html; charset=UTF-8");
    exchange.sendResponseHeaders(200, response.getBytes().length);

    try (OutputStream os = exchange.getResponseBody()) {
      os.write(response.getBytes());
    }
  }

}
