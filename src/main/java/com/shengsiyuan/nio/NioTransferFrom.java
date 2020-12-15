package com.shengsiyuan.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NioTransferFrom {

    public static void main(String[] args) throws Exception {


        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);

        fromChannel.close();
        toChannel.close();

        fromFile.close();
        toFile.close();
    }
}
