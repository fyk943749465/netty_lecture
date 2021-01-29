package com.shengsiyuan.nettyv2.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;

public class String2IntegerEncoderTest {

    public static void main(String[] args) throws InterruptedException {

        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new Integer2ByteEncoder());
                ch.pipeline().addLast(new String2IntegerEncoder());
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(i);
        for (int j = 0; j < 100; ++j) {
            String s = "i am " + j;
            channel.write(s);
        }

        channel.flush();
        ByteBuf buf = (ByteBuf)channel.readOutbound();
        while (null != buf) {
            System.out.println("o = " + buf.readInt());
            buf = (ByteBuf)channel.readOutbound();
        }

        Thread.sleep(Integer.MAX_VALUE);
    }
}
