package com.shengsiyuan.dp.proxy.dynamic;

public class Client {

    public static void main(String[] args) {

        // 创建目标对象
        ITeacherDao target = new TeacherDao();

        // 给目标对象，创建代理对象，可以转成ITeacherDao
        ITeacherDao proxyInstance = (ITeacherDao)(new ProxyFactory(target).getProxyInstance());

        // proxyInstance=com.shengsiyuan.dp.proxy.dynamic.TeacherDao@3ada9e37 内存中动态生成了代理对象
        System.out.println("proxyInstance=" + proxyInstance);

//        proxyInstance.teach();

        proxyInstance.sayHello("nihao");
    }
}
