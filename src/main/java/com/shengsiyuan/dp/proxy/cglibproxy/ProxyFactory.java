package com.shengsiyuan.dp.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {

    //　维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 返回一个代理对象，是target对象的代理对象
    public Object getProxyInstance() {
        // 1. 创建一个工具类
        Enhancer enhancer = new Enhancer();
        // 2. 设置父类
        enhancer.setSuperclass(target.getClass());
        // 3. 设置回调函数
        enhancer.setCallback(this);
        // 4. 创建子类对象，即代理对象
        return enhancer.create();
    }

    // 这个对象，会调用目标对象的相关方法
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Cglib代理模式~~　开始");
        Object invoke = method.invoke(target, args);
        System.out.println("Cglib代理模式~~ 提交");
        return invoke;
    }
}
