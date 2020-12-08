package com.shengsiyuan.netty.sixthexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyClient {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyClientChannelInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            channel.closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
