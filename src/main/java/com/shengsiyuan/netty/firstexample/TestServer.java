package com.shengsiyuan.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws Exception {


        // 一般服务端都会启动两个时间循环组，一个boss循环组，一个worker循环组
        // boss循环组主要接收客户端的连接
        // worker主要针对message进行处理，处理完成后，又如何响应给客户端
        // 时间循环组，就是一个死循环，网络编程离不开死循环
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            // 启动服务器时候，需要做一些启动之前的工作，ServerBootstrap是一个辅助类或者简化类
            // 简化netty服务器启动之前的配置工作
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 方法链的编程风格
            // 这里有handler和childHandler方法，handler方法是针对bossgroup来做的一些操作
            // 而childHandler是针对workerGroup来做的一些处理
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            // 服务器真正启动，是在这一行代码，绑定一个端口号，然后调用一个阻塞的sync方法，等待客户端的连接
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            // 连接完成后，将通道关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            // netty的优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
