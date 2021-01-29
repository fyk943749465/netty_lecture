package com.shengsiyuan.nio.book.crazymakercircle.util;

public class Print {

    /**
     * 信息输出
     * @param s　输出的字符串形参
     */
    public static void o(Object s) {
        System.out.println(s);
    }

    /**
     * 带着方法名输出，方法名称放在前面
     * @param s
     */
    public static void fo(Object s) {
        System.out.println(ReflectionUtil.getCallClassMethod() + ":" + s);
    }

    /**
     * 带着类名＋方法名输出
     * @param s
     */
    synchronized public static void cfo(Object s) {
        System.out.println(ReflectionUtil.getCallClassMethod() + ":" + s);
    }

    synchronized public static void tcfo(Object s) {
        String cft = "[" + Thread.currentThread().getName() + "|" + ReflectionUtil.getNakeClassClassMethod() + "]";
        System.out.println(cft + ":" + s);
    }

    public static void hint(Object s) {
        System.out.println("/--" + s + "--/");
    }
}
