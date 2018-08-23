package com.nix.fast.simple.api.util;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author Kiss
 * @date 2018/08/23 14:08
 */
public class AcceptTask implements Runnable{

    private final SocketChannel socketChannel;

    public AcceptTask(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            while (socketChannel.read(buffer) > 0) {

            }
            System.out.println(buffer.capacity());
        }catch (Exception e) {

        }
    }
}
