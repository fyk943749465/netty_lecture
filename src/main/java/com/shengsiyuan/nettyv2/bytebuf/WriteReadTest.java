package com.shengsiyuan.nettyv2.bytebuf;

import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import static com.shengsiyuan.nettyv2.bytebuf.PrintAttribute.print;

public class WriteReadTest {

    public void testWriteRead() {

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        print("动作：分配　ByteBuf(9, 100)", buffer);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        print("动作：写入4个字节(1,2,3,4)", buffer);
        Logger.info("start============:get================");
        getByteBuf(buffer);
        print("动作：取数据ByteBuf", buffer);
        Logger.info("start===========:read================");
        readByteBuf(buffer);
        print("动作：读完 ByteBuf", buffer);
    }

    // 读取一个字节
    private void readByteBuf(ByteBuf buffer) {
        while(buffer.isReadable()) {
            Logger.info("读取一个字节:" + buffer.readByte());
        }
    }

    // 读取一个字节，不改变指针
    private void getByteBuf(ByteBuf buffer) {
        for (int i = 0; i < buffer.readableBytes(); ++i) {
            Logger.info("读取一个字节:" + buffer.getByte(i));
        }
    }

    public static void main(String[] args) {
        WriteReadTest writeReadTest = new WriteReadTest();
        writeReadTest.testWriteRead();
    }
}
