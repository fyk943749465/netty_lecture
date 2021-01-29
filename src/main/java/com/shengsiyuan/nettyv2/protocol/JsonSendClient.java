package com.shengsiyuan.nettyv2.protocol;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class JsonSendClient {

    static String content = "疯狂创客园：高性能学习社群";

    private int serverPort;
    private String serverIp;

    Bootstrap b = new Bootstrap();

    public JsonSendClient(String ip, int port) {
        this.serverPort = port;
        this.serverIp = ip;
    }

    public void runClient() {
        // 创建reactor 线程组
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup();

        try {
            //1. 设置reactor线程组
            b.group(workerLoopGroup)
            //2. 设置nio类型的channel
            .channel(NioSocketChannel.class)
            //3. 设置监听端口
            .remoteAddress(serverIp, serverPort)
            //4. 设置通道的参数
            .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)

            //5. 转配通道流水线
            .handler(new ChannelInitializer<SocketChannel>() {
                //　初始化客户端channel
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    // 客户端channel流水线添加2个handler处理器
                    ch.pipeline().addLast(new LengthFieldPrepender(4));
                    ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                }
            });

            ChannelFuture f = b.connect();
            f.addListener((ChannelFuture futureListener) -> {

                if (futureListener.isSuccess()) {
                    Logger.info("EchoClient客户端连接成功!");
                } else {
                    Logger.info("EchoClient客户端连接失败!");
                }
            });

            // 阻塞，直到连接完成
            f.sync();
            Channel channel = f.channel();

            // 发送Json　字符串对象
            for (int i = 0; i < 1000; ++i) {
                JsonMsg user = build(i, i + "->" + content);
                channel.writeAndFlush(user.convertToJson());
                Logger.info("发送报文:" + user.convertToJson());
            }
            channel.flush();

            // 等待通道关闭的异步任务结束
            // 服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channel.closeFuture();
            closeFuture.sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅关闭EventLoopGroup
            // 释放掉所有资源包括创建的线程
            workerLoopGroup.shutdownGracefully();
        }
    }

    // 构建Json对象
    public JsonMsg build(int id, String content) {
        JsonMsg user = new JsonMsg();
        user.setId(id);
        user.setContent(content);
        return user;
    }

    public static void main(String[] args) {
        int port = NioDemoConfig.SOCKET_SERVER_PORT;
        String ip = NioDemoConfig.SOCKET_SERVER_IP;
        new JsonSendClient(ip, port).runClient();
    }
}
