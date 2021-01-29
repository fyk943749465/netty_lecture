package com.shengsiyuan.nio.book.crazymakercircle.ReactorModel;

import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoHandler implements Runnable {


    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
    static final int RECEIVEING = 0, SENDING = 1;

    int state = RECEIVEING;

    // 引入线程池
    static ExecutorService pool = Executors.newFixedThreadPool(4);

    MultiThreadEchoHandler(Selector selector, SocketChannel c) throws IOException {
        channel = c;
        c.configureBlocking(false);
        // 仅仅获得选择键　后设置感兴趣的IO事件
        sk = channel.register(selector, 0);
        // 将本handler 作为sk选择键的附件，方便事件dispatch
        sk.attach(this);
        // 向sk选择键注册Read就绪事件
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    @Override
    public void run() {
        // 异步任务，在独立的线程池中执行
        pool.execute(new AsyncTask());
    }

    public synchronized void asyncRun() {
        try {
            if (state == SENDING) {
                channel.write(byteBuffer);
                byteBuffer.clear();
                sk.interestOps(SelectionKey.OP_READ);
                // 写完后进入接收状态
                state = RECEIVEING;
            } else if (state == RECEIVEING) {
                // 从通道读
                int length = 0;
                while ((length = channel.read(byteBuffer)) > 0) {
                    Logger.info(new String(byteBuffer.array(), 0, length));
                }
                // 读完后，准备开始写入通道，byteBuffer切换成读模式
                byteBuffer.flip();
                // 读完后，注册writer就绪事件
                sk.interestOps(SelectionKey.OP_WRITE);
                // 读完后，进入发送状态
                state = SENDING;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // 异步任务的内部类
    class AsyncTask implements Runnable {
        public void run() {
            MultiThreadEchoHandler.this.asyncRun();
        }
    }
}
