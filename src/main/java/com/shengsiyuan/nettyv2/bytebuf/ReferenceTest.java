package com.shengsiyuan.nettyv2.bytebuf;

import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ReferenceTest {

    public static void main(String[] args) {

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        Logger.info("after create: " + buffer.refCnt());
        buffer.retain();
        Logger.info("after retain: " + buffer.refCnt());
        buffer.release();
        Logger.info("after release: " + buffer.refCnt());
        buffer.release();
        Logger.info("after release: " + buffer.refCnt());
        // 错误　refCnt 0 不能再retain
        buffer.retain();
        Logger.info("after retain: " + buffer.refCnt());
    }
}
