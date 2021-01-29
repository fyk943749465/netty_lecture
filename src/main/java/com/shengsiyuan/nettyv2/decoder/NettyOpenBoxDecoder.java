package com.shengsiyuan.nettyv2.decoder;

import com.shengsiyuan.nio.book.crazymakercircle.util.RandomUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class NettyOpenBoxDecoder {

    public static final int MAGICCODE = 9999;
    public static final int VERSION = 100;


    static String spliter = "\r\n";
    static String spliter2 = "\t";
    static String content = "疯狂创客圈：高性能学习社群!";

    /**
     * LineBasedFrameDecoder 使用实例
     */
    public void testLineBasedFrameDecoder() {
        try {
            ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
                @Override
                protected void initChannel(EmbeddedChannel ch) throws Exception {
                    ch.pipeline().addLast(new LineBasedFrameDecoder(1024)); // 行解码器解码数据
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };

            EmbeddedChannel channel = new EmbeddedChannel(i);
            for (int j = 0; j < 100; ++j) {
                int random = RandomUtil.randInMod(3);
                ByteBuf buf = Unpooled.buffer();
                for (int k = 0; k < random; ++k) {
                    buf.writeBytes(content.getBytes("UTF-8"));
                }
                buf.writeBytes(spliter.getBytes("UTF-8")); // 加入分隔符
                channel.writeInbound(buf);
            }
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * LineBasedFrameDecoder 使用实例
     */
    public void testLengthFiledBasedFrameDecoder() {

        try {
            // 第四个参数算法　内容字段偏移量 - 长度字段偏移量 - 长度字段长度
            final LengthFieldBasedFrameDecoder spliter =
                    new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4);

            ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
                @Override
                protected void initChannel(EmbeddedChannel ch) throws Exception {
                    // 用lengthFiledBasedFrameDecoder解码
                    // 五个参数的意义 1. 发送数据包的最大长度 2.长度字段偏移量　3.长度字段长度(长度字段自己占用的字节数)
                    // 4 长度字段的偏移量矫正 (内容字段偏移量 - 长度字段偏移量 - 长度字段长度)
                    // 5 丢弃的起始字节数
                    ch.pipeline().addLast(spliter);
                    ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };

            EmbeddedChannel channel = new EmbeddedChannel(i);
            for (int j = 0; j < 100; ++j) {
                // 1 - 3 之间的随机数
                int random = RandomUtil.randInMod(3);
                ByteBuf buf = Unpooled.buffer();
                byte[] bytes = content.getBytes("UTF-8");
                buf.writeInt(bytes.length * random); //用一个字段保存内容的长度
                for (int k = 0; k < random; ++k) {
                    buf.writeBytes(bytes); // 写入内容
                }
                channel.writeInbound(buf);
            }

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testDelimiterBasedFrameDecoder() {
        try {
            final ByteBuf delimiter = Unpooled.copiedBuffer(spliter2.getBytes("UTF-8"));
            ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
                @Override
                protected void initChannel(EmbeddedChannel ch) throws Exception {
                    ch.pipeline().addLast(
                        new DelimiterBasedFrameDecoder(1024, true, delimiter)// 分隔符解码器
                    );
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };

            EmbeddedChannel channel = new EmbeddedChannel(i);
            for (int j = 0; j < 100; ++j) {
                // 1 - 3 之间的随机数
                int random = RandomUtil.randInMod(3);
                ByteBuf buf = Unpooled.buffer();
                for (int k = 0; k < random; ++k) {
                    buf.writeBytes(content.getBytes("UTF-8")); //　写入内容
                }
                buf.writeBytes(spliter2.getBytes("UTF-8")); // 写入内容分隔符
                channel.writeInbound(buf);
            }

            Thread.sleep(Integer.MAX_VALUE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testLengthFiledBasedFrameDecoder1() {
        try {
            final LengthFieldBasedFrameDecoder spliter =
                    new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4);
            ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
                @Override
                protected void initChannel(EmbeddedChannel ch) throws Exception {
                    ch.pipeline().addLast(spliter);
                    ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };

            EmbeddedChannel channel = new EmbeddedChannel(i);
            for(int j = 1; j <= 100; j++) {
                ByteBuf buf = Unpooled.buffer();
                String s = j + "次发送->" + content;
                byte[] bytes = s.getBytes("UTF-8");
                buf.writeInt(bytes.length);
                System.out.println("bytes length = " + bytes.length);
                buf.writeBytes(bytes);
                channel.writeInbound(buf);
            }

            Thread.sleep(Integer.MAX_VALUE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testLengthFieldBasedFrameDecoder2() {

        try {
            final LengthFieldBasedFrameDecoder spliter =
                    new LengthFieldBasedFrameDecoder(1024, 0, 4, 2, 6);

            ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
                @Override
                protected void initChannel(EmbeddedChannel ch) throws Exception {
                    ch.pipeline().addLast(spliter);
                    ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };

            EmbeddedChannel channel = new EmbeddedChannel(i);
            for (int j = 1; j < 100; j++) {
                ByteBuf buf = Unpooled.buffer();
                String s = j + "次发送->" + content;
                byte[] bytes = s.getBytes("UTF-8");
                buf.writeInt(bytes.length); // 长度
                buf.writeChar(VERSION); // 版本号
                buf.writeBytes(bytes); // 内容
                channel.writeInbound(buf);
            }

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testLengthFieldBasedFrameDecoder3() {
        try {
            final LengthFieldBasedFrameDecoder spliter =
                    new LengthFieldBasedFrameDecoder(1024, 2, 4, 4, 10);

            ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {

                @Override
                protected void initChannel(EmbeddedChannel ch) throws Exception {
                    ch.pipeline().addLast(spliter);
                    ch.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
                    ch.pipeline().addLast(new StringProcessHandler());
                }
            };

            EmbeddedChannel channel = new EmbeddedChannel(i);

            for (int j = 1; j <= 100; ++j) {
                ByteBuf buf = Unpooled.buffer();
                String s = j + "次发送->" + content;
                byte[] bytes = s.getBytes("UTF-8");
                buf.writeChar(VERSION);
                buf.writeInt(bytes.length);
                buf.writeInt(MAGICCODE);
                buf.writeBytes(bytes);
                channel.writeInbound(buf);
            }

            Thread.sleep(Integer.MAX_VALUE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NettyOpenBoxDecoder nettyOpenBoxDecoder = new NettyOpenBoxDecoder();
//        nettyOpenBoxDecoder.testLengthFiledBasedFrameDecoder();
//        nettyOpenBoxDecoder.testDelimiterBasedFrameDecoder();
//        nettyOpenBoxDecoder.testLengthFiledBasedFrameDecoder1();
//        nettyOpenBoxDecoder.testLengthFieldBasedFrameDecoder2();
        nettyOpenBoxDecoder.testLengthFieldBasedFrameDecoder3();
    }
}
