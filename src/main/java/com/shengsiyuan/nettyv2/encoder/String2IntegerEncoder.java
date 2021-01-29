package com.shengsiyuan.nettyv2.encoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class String2IntegerEncoder extends MessageToMessageEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, List<Object> out) throws Exception {
        char[] array = msg.toCharArray();
        for (char a : array) {
            System.out.println("a: " + a);
            if (a >= 48 && a<= 57) {
                out.add(Integer.valueOf(a));
            }
        }
    }
}
