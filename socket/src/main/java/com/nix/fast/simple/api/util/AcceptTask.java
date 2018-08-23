package com.nix.fast.simple.api.util;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author Kiss
 * @date 2018/08/23 14:08
 */
public class AcceptTask implements Runnable{

    private final SelectionKey key;

    public AcceptTask(SelectionKey key) {
        this.key = key;
    }
    @Override
    public void run() {
        final SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            while (socketChannel.read(buffer) > 0) {
            }
            ByteBuffer write = ByteBuffer.wrap("hello world".getBytes());
            socketChannel.write(write);
        }catch (Exception ignored) {

        }
        key.cancel();
    }
}
