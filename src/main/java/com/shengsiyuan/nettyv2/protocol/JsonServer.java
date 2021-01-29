package com.shengsiyuan.nettyv2.protocol;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

public class JsonServer {

    private final int serverPort;
    ServerBootstrap b = new ServerBootstrap();

    public JsonServer(int port) {
        this.serverPort = port;
    }

    public void runServer() {

        // 创建Reactor线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workderLoopGroup = new NioEventLoopGroup();

        try {
            // 1 设置reactor线程组
            b.group(bossLoopGroup, workderLoopGroup)
            // 2 设置nio类型的channel
            .channel(NioServerSocketChannel.class)
            // 3 设置监听端口
            .localAddress(serverPort)
            // 4 设置通道的参数
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)

            // 5 装配子通道流水线
            .childHandler(new ChannelInitializer<SocketChannel>() {

                // 有连接到达时会创建一个channel
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // pipeline 管理子通道Channel中的Handler
                    // 向子channel流水线添加３个handler处理器
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                    ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                    ch.pipeline().addLast(new JsonMsgDecoder());
                }
            });

            // 6. 开始绑定server
            // 通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = b.bind().sync();
            Logger.info("服务器启动成功，监听端口: " +
                    channelFuture.channel().localAddress());

            // 7 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
             // 8 优雅关闭EventLoopGroup
             // 释放掉所有资源包括创建的线程
            workderLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
    }

    // 服务器端业务处理器
    static class JsonMsgDecoder extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            String json = (String)msg;
            JsonMsg jsonMsg = JsonMsg.parseFromJson(json);
            Logger.info("收到一个　Json 数据包 => " + jsonMsg);
        }
    }

    public static void main(String[] args) {
        int port = NioDemoConfig.SOCKET_SERVER_PORT;
        new JsonServer(port).runServer();
    }
}
