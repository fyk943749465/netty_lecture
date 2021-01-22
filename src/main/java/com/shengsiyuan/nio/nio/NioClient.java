package com.shengsiyuan.nio.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {

    public static void main(String[] args) throws Exception {

        try {
            // 客户端这边　获取一个channel
            SocketChannel socketChannel = SocketChannel.open();
            // 也将channel设置为非阻塞的
            socketChannel.configureBlocking(false);

            // 获取selector
            Selector selector = Selector.open();
            // 将channel注册到selector 注册的事件为连接事件
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            // 客户端的channel连接到服务端
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

            while (true) {
                // 获取注册的channel
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                for (SelectionKey selectionKey : keySet) {
                    // 如果已经连接到服务端
                    if (selectionKey.isConnectable()) {
                        // 获取注册的channel
                        SocketChannel client = (SocketChannel)selectionKey.channel();
                        // 判断此通道是是否有连接操作
                        if (client.isConnectionPending()) {
                            // 如有有连接操作，首先完成连接
                            client.finishConnect();
                            // 接着将数据通过该通道，写到服务端
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

                            writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);
                            //　获取一个线程池
                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            // 从线程池中创建线程，该线程主要功能：获取从键盘上的输入，将输入的数据发送给服务器
                            executorService.submit(() -> {
                                while(true) {
                                    try {
                                        writeBuffer.clear();
                                        InputStreamReader input = new InputStreamReader(System.in);
                                        BufferedReader br = new BufferedReader(input);

                                        String sendMessage = br.readLine();

                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();
                                        client.write(writeBuffer);
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                        }
                        client.register(selector, SelectionKey.OP_READ);
                    }
                    // 判断该通道上是否有数据可读
                    else if (selectionKey.isReadable()) {
                        // 如果有数据可以断，那么获取到该通道
                        SocketChannel client = (SocketChannel)selectionKey.channel();
                        // 创建buffer，准备获取数据
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        // 将该通道的上的数据读入buffer中
                        int count = client.read(readBuffer);
                        // 将读到的数据，回显
                        if (count > 0) {
                            String receivedMessage = new String(readBuffer.array(), 0, count);
                            System.out.println(receivedMessage);
                        }
                    }
                    // 清空keySet
                    keySet.clear();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
