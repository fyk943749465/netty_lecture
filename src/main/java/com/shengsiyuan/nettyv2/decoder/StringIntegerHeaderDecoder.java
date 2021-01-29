package com.shengsiyuan.nettyv2.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class StringIntegerHeaderDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        // 可读字节小于４，消息头还没读满，返回
        if (internalBuffer().readableBytes() < 4) {
            return;
        }


        // 消息头已经完整
        // 在真正开始从缓冲区读取数据之前，调用markReaderIndex() 设置回滚点
        // 回滚点为消息头的readIndex指针位置
        in.markReaderIndex();
        int length = in.readInt();
        //　从缓冲区中读取消息头的大小，这回使得readIndex读指针前移
        // 剩余长度不够消息体，重置读指针
        if (in.readableBytes() < length) {
            // 读指针回滚到消息头的readIndex位置，未进行状态保存
            in.resetReaderIndex();
            return ;
        }

        // 读取数据，编码成字符串
        byte[] inBytes = new byte[length];
        in.readBytes(inBytes, 0, length);
        out.add(new String(inBytes, "UTF-8"));
    }
}
