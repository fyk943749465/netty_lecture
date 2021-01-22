package com.shengsiyuan.dp.flyweight;

public class Client {

    // 用户是外部状态
    // 类型type为内部状态，即博客，新闻等，只有在内部状态不同的时候，才去创建实例．
    public static void main(String[] args) {

        // 创建一个工厂类
        WebSiteFactory factory = new WebSiteFactory();

        //客户要一个以新闻形式发布的网站
        WebSite webSite1 = factory.getWebSiteCategory("新闻");

        webSite1.use(new User("tom"));

        WebSite webSite2 = factory.getWebSiteCategory("博客");
        webSite2.use(new User("jack"));

        WebSite webSite3 = factory.getWebSiteCategory("博客");
        webSite3.use(new User("smith"));

        WebSite webSite4 = factory.getWebSiteCategory("博客");
        webSite4.use(new User("king"));

        System.out.println("网站的分类共:" + factory.getWebSiteConcrete());

        Integer.valueOf(1);
    }
}
