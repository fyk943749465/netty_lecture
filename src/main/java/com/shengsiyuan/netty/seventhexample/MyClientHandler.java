package com.shengsiyuan.netty.seventhexample;

import protobuf.MyMessageInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<MyMessageInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessageInfo.MyMessage msg) throws Exception {

        System.out.println("receive from server: ");
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
        System.out.println("-------------------------------------");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("send msg to server ");

        MyMessageInfo.MyMessage msg = MyMessageInfo.MyMessage.newBuilder().setMsgType(MyMessageInfo.MyMessage.MessageType.StudentType)
                .setStudent(
                        MyMessageInfo.Student.newBuilder()
                        .setName("zhangsan")
                        .setAge(18)
                        .setAddress("HongKong")
                        .build()
                ).build();

        ctx.writeAndFlush(msg);
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
