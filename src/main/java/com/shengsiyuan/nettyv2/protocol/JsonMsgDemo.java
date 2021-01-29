package com.shengsiyuan.nettyv2.protocol;

import com.shengsiyuan.nio.book.crazymakercircle.util.Logger;

import java.io.IOException;

public class JsonMsgDemo {

    // 构建Json对象
    public JsonMsg buildMsg() {
        JsonMsg user = new JsonMsg();
        user.setId(1000);
        user.setContent("疯狂创客园：高性能学习社群");
        return user;
    }


    // 序列化　serialization & 反序列化　Deserialization
    public void serAndDesr() throws IOException {

        JsonMsg message = buildMsg();

        // 将Pojo对象，序列化成字符串
        String json = message.convertToJson();
        // 可以用于网络传输，保存到内存和外存
        Logger.info("json:=" + json);

        // JSON字符串，反序列化成对象POJO
        JsonMsg inMsg = JsonMsg.parseFromJson(json);
        Logger.info("id:=" + inMsg.getId());
        Logger.info("content:=" + inMsg.getContent());
    }

    public static void main(String[] args) throws IOException {

        JsonMsgDemo jsonMsgDemo = new JsonMsgDemo();
        jsonMsgDemo.serAndDesr();
    }
}
