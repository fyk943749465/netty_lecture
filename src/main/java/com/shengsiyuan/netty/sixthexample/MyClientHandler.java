package com.shengsiyuan.netty.sixthexample;

import protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

        System.out.println("receive from server: ");
        System.out.println(msg.getName());
        System.out.println(msg.getAddress());
        System.out.println(msg.getAge());
        System.out.println("-------------------------------------");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("send msg to server ");
        DataInfo.Student student = DataInfo.Student.newBuilder()
        .setName("zhangsan")
        .setAddress("guangzhou")
        .setAge(20)
        .build();

        ctx.writeAndFlush(student);
        System.out.println("send msg to server finished");
    }

    //    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
//
//        System.out.println("receive from server: ");
//        System.out.println(ctx.channel().remoteAddress());
//
//        DataInfo.Student student = DataInfo.Student.parseFrom(msg);
//        System.out.println(student.getName());
//        System.out.println(student.getAge());
//        System.out.println(student.getAddress());
//        System.out.println("-------------------------------------");
//    }
//
//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//
//        System.out.println("send msg to server ");
//        DataInfo.Student student = DataInfo.Student.newBuilder()
//                .setName("zhangsan")
//                .setAddress("guangzhou")
//                .setAge(20)
//                .build();
//
//        byte[] s2ba = student.toByteArray();
//
//        ctx.writeAndFlush(s2ba);
//        System.out.println("send msg to server finished");
//    }


}
