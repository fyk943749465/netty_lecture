package com.shengsiyuan.nio.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        // 获取channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置channel为异步的
        serverSocketChannel.configureBlocking(false);
        // 获取一个socket
        ServerSocket serverSocket = serverSocketChannel.socket();
        // socket绑定到一个端口
        serverSocket.bind(new InetSocketAddress(8899));

        // 获取selector
        Selector selector = Selector.open();
        // 将channel绑定到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                // 一旦我们向selector注册了一个或多个channel后，就可以调用select来获取channel
                // select方法会返回所有处于就绪状态的channel，select方法在返回之前，处于阻塞状态
                // select() 方法的返回值是一个int整形，代表有多少channel处于就绪了．也就是自上一次select之后
                // 有多少channel进入就绪．举例来说，假设第一次调用select时正好有一个channel就绪，那么返回值是１
                // 并且对这个channel做任何处理，接着再次调用select，此时恰好又一个新的channel就绪，那么返回值还是１，
                // 现在我们一共有两个channel处于就绪状态，但是每次调用select时只有一个channel是就绪的
                selector.select();
                // 在调用select并返回了有channel就绪之后，可以通过选中的key集合来获取channel，这个操作调用selectedKeys方法
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;

                    try {
                        // 判断现在是否可以接收客户端的连接
                        if (selectionKey.isAcceptable()) {
                            // 获取服务端的channel
                            ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
                            // 通过服务端的channel获取到客户端连接过来的channel
                            client = server.accept();
                            // 配置channel为异步的非阻塞的
                            client.configureBlocking(false);
                            // 将该channel注册到selector
                            client.register(selector, SelectionKey.OP_READ);
                            // 生成uuid
                            String uuid = UUID.randomUUID().toString();
                            // 用client　map维持客户端的连接
                            clientMap.put(uuid, client);
                        }
                        // 判断是否可以读取客户端发过来的数据
                        else if (selectionKey.isReadable()) {
                            // 获取客户端连接过来的channel
                            client = (SocketChannel)selectionKey.channel();
                            // 创建buffer,准备读取客户端发过来的数据
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            // 从channel中将数据读入buffer中
                            int count = client.read(readBuffer);
                            if (count > 0) {
                                // 读取完成，记得添加flip操作
                                readBuffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());
                                System.out.println(client + ":" + receivedMessage);

                                String senderKey = null;
                                // 从客户端连接的集合中，找到当前的客户端连接过来的channel
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }

                                // 从服务端　将数据写到所有连接的客户端
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    SocketChannel value = entry.getValue();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                    writeBuffer.put((senderKey + ":" + receivedMessage).getBytes());
                                    writeBuffer.flip();
                                    value.write(writeBuffer);
                                }
                            }
                        }
                    } catch (Exception ex1) {
                        ex1.printStackTrace();
                    }
                });
                // 用完之后，清空key
                selectionKeys.clear();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
