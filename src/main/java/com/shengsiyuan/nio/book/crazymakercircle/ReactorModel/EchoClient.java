package com.shengsiyuan.nio.book.crazymakercircle.ReactorModel;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.Dateutil;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;
import com.shengsiyuan.nio.book.crazymakercircle.util.Print;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class EchoClient {

    public void start() throws IOException {

        // 连接服务端的地址（ip和端口号）
        InetSocketAddress address =
                new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP,
                        NioDemoConfig.SOCKET_SERVER_PORT);

        // get channel（用一个SocketChannel去连接一个地址）
        SocketChannel socketChannel = SocketChannel.open(address);
        // change to no block（设置channel为异步的）
        socketChannel.configureBlocking(false);
        // 不断自旋，等待连接建立成功
        while (!socketChannel.finishConnect()) {

        }
        Print.tcfo("客户端启动成功");

        // 启动接收线程
        Processer processer = new Processer(socketChannel);
        new Thread(processer).start();
    }

    static class Processer implements Runnable {

        final Selector selector;
        final SocketChannel channel;

        Processer(SocketChannel channel) throws IOException {
            // 将channel中感兴趣的事件与selector关联
            selector = Selector.open();
            this.channel = channel;
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        }

        // 线程启动要执行的方法
        @Override
        public void run() {
            try {
                // 判断线程是否被打断（当不被打断时候，进入循环）
                while (!Thread.interrupted()) {
                    // 阻塞调用（表示有多少通道处于就绪状态，当有通道处于就绪状态的时候，调用就马上返回）
                    selector.select();
                    // 循环感兴趣的事件
                    Set<SelectionKey> selected = selector.selectedKeys();
                    Iterator<SelectionKey> it = selected.iterator();
                    while (it.hasNext()) {
                        SelectionKey sk = it.next();
                        // 如果是可以写状体
                        if (sk.isWritable()) {
                            ByteBuffer buffer = ByteBuffer.allocate(NioDemoConfig.SEND_BUFFER_SIZE);

                            Scanner scanner= new Scanner(System.in);
                            Print.tcfo("please enter the message than will send:");
                            if (scanner.hasNext()) {
                                SocketChannel socketChannel = (SocketChannel)sk.channel();
                                String next = scanner.next();
                                buffer.put((Dateutil.getNow() + " >>" + next).getBytes());
                                buffer.flip();
                                // 通过DatagramChannel数据报直接发送数据
                                socketChannel.write(buffer);
                                buffer.clear();
                            }
                        }
                        if (sk.isReadable()) {
                            // 若选择键的IO事件是＂可读＂事件，读取数据
                            SocketChannel socketChannel = (SocketChannel)sk.channel();

                            // 读取数据
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int length = 0;
                            while ((length = socketChannel.read(byteBuffer)) > 0) {
                                byteBuffer.flip();
                                Logger.debug("server echo: " + new String(byteBuffer.array(), 0, length));
                                byteBuffer.clear();
                            }
                        }
                    }
                    selected.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new EchoClient().start();
    }
}
