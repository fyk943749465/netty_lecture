package com.shengsiyuan.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NewServer {

    public static void main(String[] args) throws Exception{

        InetSocketAddress address = new InetSocketAddress(8899);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket serverSocket = serverSocketChannel.socket();
        // 调用该方法，主要是为了防止绑定一个地址失败．因为假如在８８９９端口号上，原来有一个socket连接，
        // 在该连接关闭后的一小段时间内，该连接处于超时状态（time_wait）状态，如果有新的连接，尝试在该端口号上
        // 建立，那么此时就会失败．
        serverSocket.setReuseAddress(true);
        serverSocket.bind(address);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(true);

            int readCount = 0;
            while (-1 != readCount) {
                try {
                    readCount = socketChannel.read(byteBuffer);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                byteBuffer.rewind();
            }
        }

    }
}
