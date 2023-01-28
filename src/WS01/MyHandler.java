package WS01;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * Handlerをいい感じにします
 */
public abstract class MyHandler implements HttpHandler {

  private int count;

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    count += 1;
    this.printRequest(exchange);
  }

  private void printRequest(HttpExchange exchange) {
    System.out.printf("Request Message %d ---------------", count);

    String method = exchange.getRequestMethod();
    System.out.println("Method:" + method);

    String uri = exchange.getRequestURI().toString();
    System.out.println("URI:" + uri);

    System.out.println("Headers:");
    Headers headers = exchange.getRequestHeaders();
    headers.entrySet().forEach(System.out::println);
  }

}
