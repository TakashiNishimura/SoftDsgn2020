package WS01;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.stream.Stream;

public class SimpleServer {

  public static void main(String[] args) {
    final String hello = "/hello";
    final String omikuji = "/omikuji";
    final String image = "/image";
    final String dynamic = "/dynamic";

    try {
      InetSocketAddress address = new InetSocketAddress(8080);
      HttpServer httpServer = HttpServer.create(address, 0);

      httpServer.createContext(hello, new SimpleHandler());
      httpServer.createContext(omikuji, new OmikujiHandler());
      httpServer.createContext(image, new ImageHandler());
      httpServer.createContext(dynamic, new DynamicHandler());

      httpServer.start();
      System.out.println("Server is started!");
      Stream.of(hello, omikuji, image, dynamic)
          .map(path -> "http://localhost:8080" + path)
          .forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
