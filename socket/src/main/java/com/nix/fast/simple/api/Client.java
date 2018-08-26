package com.nix.fast.simple.api;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author Kiss
 * @date 2018/08/23 23:05
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(8888));
        StringBuffer byteBuffer = new StringBuffer();
        for (int i = 0;i < 1024;i ++) {
            byteBuffer.append(i + ",");
        }
        System.out.println(byteBuffer);
        socketChannel.write(ByteBuffer.wrap(byteBuffer.toString().getBytes()));
        socketChannel.close();
    }
}
