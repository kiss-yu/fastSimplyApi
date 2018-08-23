package com.nix.fast.simple.api;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author Kiss
 * @date 2018/08/23 23:05
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(8888));
        socketChannel.write(ByteBuffer.wrap("hello client".getBytes()));
        socketChannel.close();
    }
}
