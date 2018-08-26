package com.nix.fast.simple.api.util;
import com.nix.fast.simple.api.pipeline.Pipeline;
import com.nix.simple.api.http.request.HttpRequest;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author Kiss
 * @date 2018/08/23 14:08
 */
public class AcceptTask implements Runnable{

    private final SelectionKey key;
    private final Pipeline pipeline = new Pipeline();

    public AcceptTask(SelectionKey key) {
        this.key = key;
    }
    @Override
    public void run() {
        final SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketByteBuff socketByteBuff = new SocketByteBuff();
        try {
            int len;
            while ((len = socketChannel.read(buffer)) >= 0) {
                socketByteBuff.put(buffer.array(),len);
                buffer.clear();
            }
            if (socketByteBuff.isEmpty()) {
                return;
            }
            //将请求数据交给通道处理返回内容会写给客户端
            socketChannel.write(ByteBuffer.wrap(pipeline.inOpened(HttpRequest.createRequest(socketByteBuff.getBytes())).httpContentBytes()));
        }catch (Exception ignored) {

        }
        key.cancel();
    }
    private class SocketByteBuff {
        private int position = 0;
        private int size = 512;
        private volatile byte[] data = new byte[size];
        public synchronized void put(byte[] bytes,int length) {
            if (position + length > size) {
                while (reset() < position + length){};
            }
            System.arraycopy(bytes,0,data,position,length);
            position += length;
        }
        private int reset() {
            byte[] bak = new byte[size * 2];
            System.arraycopy(data,0,bak,0,size);
            data = bak;
            size *= 2;
            return size;
        }
        public byte[] getBytes() {
            return data;
        }
        public boolean isEmpty() {
            return position == 0;
        }
    }
}
