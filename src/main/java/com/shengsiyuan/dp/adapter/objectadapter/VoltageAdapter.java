package com.shengsiyuan.dp.adapter.objectadapter;

public class VoltageAdapter  implements IVoltage5V {

    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        // 获取　220V电压
        int dstV = 0;
        if (null != this.voltage220V) {
            int srcV = this.voltage220V.output220V();
            dstV = srcV / 44;
        }
        return dstV;
    }
}
