package com.shengsiyuan.dp.adapter.classadapter;

public class VoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public int output5V() {
        // 获取　220V电压
        int srcV = output220V();
        int dstV = srcV / 44;
        return dstV;
    }
}
