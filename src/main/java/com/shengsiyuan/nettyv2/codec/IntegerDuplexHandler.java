package com.shengsiyuan.nettyv2.codec;

import com.shengsiyuan.nettyv2.decoder.Byte2IntegerDecoder;
import com.shengsiyuan.nettyv2.encoder.Integer2ByteEncoder;
import io.netty.channel.CombinedChannelDuplexHandler;

public class IntegerDuplexHandler extends CombinedChannelDuplexHandler<Byte2IntegerDecoder, Integer2ByteEncoder> {

    public IntegerDuplexHandler() {
        super(new Byte2IntegerDecoder(), new Integer2ByteEncoder());
    }
}
