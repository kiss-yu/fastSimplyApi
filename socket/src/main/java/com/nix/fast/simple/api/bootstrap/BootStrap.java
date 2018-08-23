package com.nix.fast.simple.api.bootstrap;

import com.nix.fast.simple.api.util.AcceptTask;
import com.nix.fast.simple.api.util.ServerConfig;
import com.nix.fast.simple.api.util.SocketTreadPool;
import com.nix.fast.simple.api.util.log.LogKit;
import org.apache.commons.logging.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Kiss
 * @date 2018/08/23 13:56
 */
public class BootStrap {
    private final Log logger = LogKit.getLog(BootStrap.class);
    private final Selector selector = Selector.open();
    private final ServerSocketChannel server = ServerSocketChannel.open();
    private final static Object SELECTOR_CLOCK = new Object();
    private final ThreadPoolExecutor selectorPool = new ThreadPoolExecutor(30, 30, 0, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(200),
            r -> {
                Thread thread = new Thread(r);
                thread.checkAccess();
                thread.setName("selector thread");
                thread.setPriority(Thread.MAX_PRIORITY);
                return thread;
            });
    public BootStrap() throws IOException {

    }

    public void start() throws IOException {
        //bind accept port
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(ServerConfig.port));
        logger.info("server bind port success {}");
        server.register(selector, SelectionKey.OP_ACCEPT);
        logger.info("start...");
        while (true) {
            selector.select();
            selectorPool.execute(() -> {
                Iterator<SelectionKey> iterator;
                synchronized (SELECTOR_CLOCK) {
                    iterator = selector.selectedKeys().iterator();
                }
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        try {
                            SocketTreadPool.execute(new AcceptTask((SocketChannel) key.channel()));
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
                        } catch (Exception e) { }
                    }
                    iterator.remove();
                }
            });
        }
    }
}
