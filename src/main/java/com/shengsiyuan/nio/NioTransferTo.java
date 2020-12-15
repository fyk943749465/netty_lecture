package com.shengsiyuan.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NioTransferTo {

    public static void main(String[] args) throws Exception {

        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
        fromChannel.close();
        toChannel.close();

        fromFile.close();
        toFile.close();
    }
}
