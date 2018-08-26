package com.nix.fast.simple.api.bootstrap;

import com.nix.fast.simple.api.util.AcceptTask;
import com.nix.fast.simple.api.util.ServerConfig;
import com.nix.fast.simple.api.util.SocketTreadPool;
import com.nix.fast.simple.api.util.log.LogKit;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author Kiss
 * @date 2018/08/23 13:56
 */
public class BootStrap {
    private final Logger logger = LogKit.getLog(BootStrap.class);
    private final Selector selector = Selector.open();
    private final ServerSocketChannel server = ServerSocketChannel.open();
    private final static Object SELECTOR_CLOCK = new Object();
    public BootStrap() throws IOException {

    }

    public void start() throws IOException {
        //bind accept port
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(ServerConfig.port));
        logger.info("server bind port success {}",ServerConfig.port);
        server.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("start...");
        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                try {
                    if (key.isReadable()) {
                        try {
                            SocketTreadPool.execute(new AcceptTask(key));
                        }catch (Exception e) {
                            logger.error("处理请求失败");
                        }
                    }
                    else if (key.isAcceptable()) {
                        try {
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                            SocketChannel channel = serverSocketChannel.accept();
                            channel.configureBlocking(false);
                            channel.register(selector,SelectionKey.OP_READ);
                        } catch (Exception ignored) { }
                    }
                }catch (Exception ignored) {}
                iterator.remove();
            }
        }
    }
}
