package uk.co.josephearl.springboot.playground;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

@Slf4j
public class VanillaWebFluxApplication {
  public static void main(String[] args) {
    var routes = RouterFunctions.route()
      .POST("/people", RequestPredicates.accept(MediaType.APPLICATION_JSON), request -> {
        String id = "1";
        return ServerResponse.ok().bodyValue(id);
      })
      .GET("/people/{id}", request -> {
        String id = request.pathVariable("id");
        return ServerResponse.ok().bodyValue(id);
      })
      .PUT("/people/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), request -> {
        String id = request.pathVariable("id");
        return ServerResponse.ok().bodyValue(id);
      })
      .DELETE("/people/{id}", request -> {
        String id = request.pathVariable("id");
        return ServerResponse.ok().bodyValue(id);
      })
      .build();

    var handler = RouterFunctions.toHttpHandler(routes);
    var adapter = new ReactorHttpHandlerAdapter(handler);
    var host = "localhost";
    var port = 8888;
    var server = HttpServer.create().host(host).port(port).handle(adapter).bind().block();
    log.info("Server started on port: " + port);
    server.onDispose().block();
  }
}
