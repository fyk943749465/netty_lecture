package com.shengsiyuan.nettyv2.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;

public class OutHandlerDemoTest {


    public static void main(String[] args) {

        final OutHandlerDemo handler = new OutHandlerDemo();

        ChannelInitializer channelInitializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(handler);
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(channelInitializer);

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);

        ChannelFuture channelFuture = channel.pipeline().writeAndFlush(buf);
        channelFuture.addListener(future -> {
           if (future.isSuccess()) {
               System.out.println("write is finished");
           }
           channel.close();
        });

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
