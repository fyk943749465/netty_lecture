package com.shengsiyuan.nio.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioWriteToFile {

    public static void main(String[] args) throws Exception {

        RandomAccessFile randomAccessFile = new RandomAccessFile("writeToFile.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        String newData = "New String to write to file ..." + System.currentTimeMillis();

        ByteBuffer buf = ByteBuffer.allocate(48);
        buf.clear();
        buf.put(newData.getBytes());

        buf.flip();

        while(buf.hasRemaining()) {
            fileChannel.write(buf);
        }
        fileChannel.close();

        randomAccessFile.close();;

    }
}
