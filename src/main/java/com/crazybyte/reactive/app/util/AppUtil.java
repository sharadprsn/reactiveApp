package com.crazybyte.reactive.app.util;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class AppUtil {

    public static String getHostInfo() {
        String ip = StringUtil.EMPTY_STRING;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException unknownHostException) {
            log.error("Exception while getting host info", unknownHostException);
        }
        return ip;
    }
}
