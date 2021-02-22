package com.example.mylibrary.common;

import java.io.IOException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;

/**
 * Created by 任小龙 on 2021/1/19.
 */
public class ProxySelector extends java.net.ProxySelector {
    @Override
    public List<Proxy> select(URI uri) {
        return Collections.singletonList(Proxy.NO_PROXY);
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {

    }
}
