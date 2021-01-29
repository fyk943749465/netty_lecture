package com.shengsiyuan.nettyv2.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;

public class AllocatorTest {

    public static void main(String[] args) {

        ByteBuf buffer = null;
        // 方法一: 默认分配器, 分配初始容量为9, 最大容量１００的缓冲
        buffer = ByteBufAllocator.DEFAULT.buffer(9, 100);
        // 方法二: 默认分配器, 分配初始容量为256, 最大容量Integer.MAX_VALUE 的缓冲
        buffer = ByteBufAllocator.DEFAULT.buffer();
        // 方法三: 非池化分配器, 分配基于java的堆内存缓冲区
        buffer = UnpooledByteBufAllocator.DEFAULT.heapBuffer();
        // 方法四: 池化分配器，分配基于操作系统管理的直接内存缓冲区
        buffer = PooledByteBufAllocator.DEFAULT.directBuffer();
        // 其他方法
    }
}
