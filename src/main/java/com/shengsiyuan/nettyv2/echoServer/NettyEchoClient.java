package com.shengsiyuan.nettyv2.echoServer;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.Dateutil;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;
import com.shengsiyuan.nio.book.crazymakercircle.util.Print;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyEchoClient {

    private int serverPort;
    private String serverIp;
    Bootstrap b = new Bootstrap();

    public NettyEchoClient(String ip, int port) {
        this.serverIp = ip;
        this.serverPort = port;
    }

    public void runClient() {
        // 创建reactor线程组
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            b.group(workerLoopGroup)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(serverIp, serverPort)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
                        }
                    });
            ChannelFuture f = b.connect();
            f.addListener((ChannelFuture futureListener) -> {

                if (futureListener.isSuccess()) {
                    Logger.info("EchoClient 客户端连接成功!");
                } else {
                    Logger.info("EchoClient 客户端连接失败!");
                }
            });

            // 阻塞，直到连接完成
            f.sync();
            Channel channel = f.channel();

            Scanner scanner = new Scanner(System.in);
            Print.tcfo("请输入发送内容:");

            while (scanner.hasNext()) {
                // 获取输入的内容
                String next = scanner.next();
                byte[] bytes = (Dateutil.getNow() + ">>" + next).getBytes("UTF-8");
                // 发送ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
                Print.tcfo("请输入发送内容");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = NioDemoConfig.SOCKET_SERVER_PORT;
        String ip = NioDemoConfig.SOCKET_SERVER_IP;
        new NettyEchoClient(ip, port).runClient();
    }
}
