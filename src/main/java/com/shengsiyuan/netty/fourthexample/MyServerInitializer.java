package com.shengsiyuan.netty.fourthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        // 心跳包主要处理的一种情况是，客户端和服务端建立连接后，网络中断，如果没有心跳包，这种情况下
        // 客户端和服务端是无法知道网络终端的．如果有了定时心跳包机制，那么在规定时间没有收到心跳包，则认为网络中断
        // 长连接　tcp连接．　IdleStateHandler主要是为了心跳包
        // 误伤的情况：如果客户端向服务端连续传了几个心跳波后，在网络传输过程中，都被丢弃掉了，其实客户端和服务端的连接
        // 还处在建立的状态，这种情况下，会误认为连接断掉
        pipeline.addLast(new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));
        pipeline.addLast(new MyServerHandler());
    }
}
