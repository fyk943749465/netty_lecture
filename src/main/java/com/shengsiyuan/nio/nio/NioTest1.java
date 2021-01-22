package com.shengsiyuan.nio.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {

    public static void main(String[] args) {

        IntBuffer buffer = IntBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        buffer.flip();  //状态的反转  读写切换之前，需要调用

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }
}
