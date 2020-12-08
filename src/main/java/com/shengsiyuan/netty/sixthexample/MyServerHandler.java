package com.shengsiyuan.netty.sixthexample;

import protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

        System.out.println("recevie from client");
        System.out.println(msg.getName());
        System.out.println(msg.getAddress());
        System.out.println(msg.getAge());
        System.out.println("---------------------------------------");
        System.out.println("send msg to client");

        DataInfo.Student student = DataInfo.Student.newBuilder()
        .setName("wangwu")
        .setAge(33)
        .setAddress("shanghai")
        .build();

        ctx.writeAndFlush(student);
        System.out.println("sent msg to client finished");

    }
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
//
//        System.out.println("recevie from client");
//        Channel channel = ctx.channel();
//        System.out.println(channel.remoteAddress());
//
//
//        DataInfo.Student student = DataInfo.Student.parseFrom(msg);
//        System.out.println(student.getName());
//        System.out.println(student.getAddress());
//        System.out.println(student.getAge());
//
//        System.out.println("---------------------------------------");
//
//        System.out.println("send msg to client");
//        DataInfo.Student student1 = DataInfo.Student.newBuilder()
//                .setName("wangwu")
//                .setAge(33)
//                .setAddress("shanghai")
//                .build();
//
//        byte[] student2ByteArray = student.toByteArray();
//
//        channel.writeAndFlush(student2ByteArray);
//        System.out.println("sent msg to client finished");
//
//    }
}
