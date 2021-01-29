package com.shengsiyuan.nettyv2.protocol;


import com.shengsiyuan.nio.book.crazymakercircle.util.JsonUtil;
import lombok.Data;

@Data
public class JsonMsg {

    private int id;

    private String content;

    // 在通用方法中，使用阿里FashJson转成Java对象
    public static JsonMsg parseFromJson(String json) {
        return JsonUtil.jsonToPojo(json, JsonMsg.class);
    }

    // 在通用方法中，使用谷歌Gson转成字符串
    public String convertToJson() {
        return JsonUtil.pojoToJson(this);
    }

}
