package com.shengsiyuan.dp.prototype.deepclone;

import java.io.*;

public class DeepCloneableTarget implements Serializable, Cloneable {

    private String cloneName;
    private String cloneClass;

    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    // 因为该类的属性，都是String，因此我们使用默认的clone方法就可以完成
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
