package com.shengsiyuan.dp.principle.inversion;

public class DependecyInversion {

    public static void main(String[] args) {
        Person person = new Person();

        person.receive(new Email());
    }
}

class Email {
    public String getInfo() {
        return "电子邮件: hello, world";
    }
}

// 完成Person接收消息的功能
// 完成方式１　
// 1. 简单，比较容易想到
// 2. 如果我们获取的对象是微信，短信等等，则需要新增类，同时Person也要增加相应的接收方法
// 3. 解决思路：引入一个抽象的借口个IReceiver，　表示接收者，这样Person类与接口IReceiver发生依赖
//    因为Email，Weixin等等都属于接收者的房屋，他们各自实现IReceiver接口就可以了
class Person {
    public void receive(Email email) {
        System.out.println(email.getInfo());
    }
}
