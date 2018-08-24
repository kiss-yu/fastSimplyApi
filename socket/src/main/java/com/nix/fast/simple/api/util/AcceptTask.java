package com.nix.fast.simple.api.util;

import com.nix.fast.simple.api.pipeline.Pipeline;

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
    private final Pipeline pipeline = new Pipeline();

    public AcceptTask(SelectionKey key) {
        this.key = key;
    }
    @Override
    public void run() {
        final SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024*6);
        SocketByteBuff socketByteBuff = new SocketByteBuff();
        try {
            while (socketChannel.read(buffer) > 0) {
                socketByteBuff.put(buffer.array(),buffer.limit());
                buffer.clear();
            }
//            System.out.println(new String(socketByteBuff.getBytes()));
            pipeline.inOpened();
        }catch (Exception ignored) {

        }
        key.cancel();
    }
    private class SocketByteBuff {
        private byte[] data = new byte[512];
        private int position = 0;
        private int size = 512;
        public synchronized void put(byte[] bytes,int length) {
            System.out.println("-----------");
            System.out.println(new String(bytes,length));
            System.out.println("-----------");
            if (position + length > size) {
                reset();
                put(bytes,length);
            }
            System.arraycopy(bytes,0,data,position,length);
            position += length;
        }
        private void reset() {
            byte[] back = new byte[size];
            System.arraycopy(back,0,data,size,size);
            size *= 2;
        }
        public byte[] getBytes() {
            return data;
        }
    }
}
