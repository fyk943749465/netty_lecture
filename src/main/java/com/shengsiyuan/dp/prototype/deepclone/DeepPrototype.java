package com.shengsiyuan.dp.prototype.deepclone;

import java.io.*;

public class DeepPrototype implements Serializable, Cloneable {

    public String name;
    public DeepCloneableTarget deepCloneableTarget; //引用类型

    public DeepPrototype() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        // 这里完成基本数据类型和string的克隆
        deep = super.clone();
        // 处理一下引用类型
        DeepPrototype deepPrototype = (DeepPrototype)deep;
        deepPrototype.deepCloneableTarget = (DeepCloneableTarget)this.deepCloneableTarget.clone();
        return deepPrototype;
    }

    // 深拷贝
    public Object deepClone() {
        // 创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            // 序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); // 当前这个对象以对象流的方式输出

            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepPrototype copyObject = (DeepPrototype)ois.readObject();
            return copyObject;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
