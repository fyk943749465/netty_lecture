package com.shengsiyuan.nettyv2.echoServer;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;

public class NettyDumpSendClient {

    private int serverPort;
    private String serverIp;

    Bootstrap b = new Bootstrap();

    public NettyDumpSendClient(String ip, int port) {
        this.serverPort = port;
        this.serverIp = ip;
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
            f.addListener((ChannelFuture futrueListener) -> {
               if (futrueListener.isSuccess()) {
                   Logger.info("EchoClient客户端连接成功");
               } else {
                   Logger.info("EchoClient客户端连接失败");
               }
            });

            // 阻塞，直到连接完成
            f.sync();
            Channel channel = f.channel();

            // 发送大量文字
            byte[] bytes = "疯狂创客圈：高性能学习社群!".getBytes(Charset.forName("utf-8"));
            for (int i = 0; i < 1000; ++i) {
                // 发送ByteBuf
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
            }

            // 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channel.closeFuture();
            closeFuture.sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = NioDemoConfig.SOCKET_SERVER_PORT;
        String ip = NioDemoConfig.SOCKET_SERVER_IP;
        new NettyDumpSendClient(ip, port).runClient();
    }
}
