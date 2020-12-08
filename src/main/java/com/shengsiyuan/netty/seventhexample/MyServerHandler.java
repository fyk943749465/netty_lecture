package com.shengsiyuan.netty.seventhexample;

import protobuf.MyMessageInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<MyMessageInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageInfo.MyMessage msg) throws Exception {

        System.out.println("recevie from client");
        MyMessageInfo.MyMessage.MessageType msgType = msg.getMsgType();

        if (msgType == MyMessageInfo.MyMessage.MessageType.StudentType) {
            MyMessageInfo.Student student = msg.getStudent();
            System.out.println(student.getName());
            System.out.println(student.getAge());
            System.out.println(student.getAddress());
        } else if (msgType == MyMessageInfo.MyMessage.MessageType.SchoolType) {
            MyMessageInfo.School school = msg.getSchool();
            System.out.println(school.getSchoolName());
        } else if (msgType == MyMessageInfo.MyMessage.MessageType.HobbyType) {
            MyMessageInfo.Hobby hobby = msg.getHobby();
            System.out.println(hobby.getName());
        } else {

        }

        System.out.println("---------------------------------------");
        System.out.println("send msg to client");

        MyMessageInfo.MyMessage newMsg =  MyMessageInfo.MyMessage.newBuilder()
                .setMsgType(MyMessageInfo.MyMessage.MessageType.SchoolType)
                .setSchool(MyMessageInfo.School.newBuilder()
                                .setSchoolName("Beijing High School No.Four")
                                .build())
                .build();
        ctx.writeAndFlush(newMsg);

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
