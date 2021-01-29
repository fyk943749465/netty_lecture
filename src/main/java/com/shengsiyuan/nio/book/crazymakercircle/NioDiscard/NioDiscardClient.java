package com.shengsiyuan.nio.book.crazymakercircle.NioDiscard;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioDiscardClient {


    public static void main(String[] args) throws IOException {
        startClient();
    }

    private static void startClient() throws IOException {

        InetSocketAddress address =
                new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP,
                        NioDemoConfig.SOCKET_SERVER_PORT);

        // 获取通道
        SocketChannel socketChannel = SocketChannel.open(address);
        // 切换成非阻塞模式
        socketChannel.configureBlocking(false);
        // 不断自旋，等待连接完成，或者做一些其他的事情
        while (!socketChannel.finishConnect()) {

        }
        Logger.info("客户端连接成功");
        // 分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello world".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }
}
