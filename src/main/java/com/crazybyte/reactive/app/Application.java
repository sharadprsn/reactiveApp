package com.crazybyte.reactive.app;

import com.crazybyte.reactive.app.handler.MetricHandler;
import com.crazybyte.reactive.app.util.AppUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

@Slf4j
public class Application {
    public static void main(String[] args) {
        DisposableServer server =
                HttpServer.create()
                        .port(9090)
                        .route(routes ->
                                routes.get("/hello", MetricHandler.pingHandler))
                        .bindNow();
        log.info("Running app on server {}, port {}", AppUtil.getHostInfo(), server.port());
        server.onDispose().block();
    }
}
