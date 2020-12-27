package com.crazybyte.reactive.app.handler;

import com.crazybyte.reactive.app.util.AppUtil;
import reactor.core.publisher.Mono;
import reactor.netty.NettyOutbound;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

import java.util.function.BiFunction;

public class MetricHandler {

    public static BiFunction<HttpServerRequest, HttpServerResponse, NettyOutbound> pingHandler = (req, res) ->
            res.sendString(Mono.just("Hello from ".concat(AppUtil.getHostInfo())));
}
