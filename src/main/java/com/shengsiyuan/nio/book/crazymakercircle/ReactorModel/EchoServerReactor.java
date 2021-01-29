package com.shengsiyuan.nio.book.crazymakercircle.ReactorModel;

import com.shengsiyuan.nio.book.crazymakercircle.NioDemoConfig;
import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServerReactor implements Runnable {

    Selector selector;
    ServerSocketChannel serverSocket;

    EchoServerReactor() throws IOException {
        // Reactor初始化

        // 获取selector
        selector = Selector.open();
        // 打开一个通道
        serverSocket = ServerSocketChannel.open();

        InetSocketAddress address =
                new InetSocketAddress(NioDemoConfig.SOCKET_SERVER_IP,
                        NioDemoConfig.SOCKET_SERVER_PORT);

        // 在scoket上绑定一个地址
        serverSocket.socket().bind(address);

        // no blocking　设置channel为异步
        serverSocket.configureBlocking(false);

        // receive accept event　channel上注册感兴趣的事件到selector上，关联channel和selector
        SelectionKey sk =
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        //　注册特定事件发生时候的回调函数
        sk.attach(new AcceptorHandler());
    }


    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    SelectionKey sk = it.next();
                    dispatch(sk);
                }
                selected.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey sk) {

        // 取回当前的acttachment,也就调用attach方法关联的回调对象
        Runnable handler = (Runnable)sk.attachment();
        // 调用之前attach绑定到选择键的handler处理器对象
        if (handler != null) {
            handler.run();
        }
    }


    // new connection handler
    class AcceptorHandler implements Runnable {

        public void run() {
            try {
                SocketChannel channel = serverSocket.accept();
                if (channel != null) {
                    Logger.debug("receive a connection");
                    new EchoHandler(selector, channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }
}
