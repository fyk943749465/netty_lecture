package com.shengsiyuan.dp.flyweight;

import java.util.HashMap;

// 网站工厂类，根据需要返回一个网站
public class WebSiteFactory {

    // 集合，充当池的作用
    private HashMap<String, ConcreteWebSite> pool = new HashMap<>();

    // 根据网站类型，返回一个网站，如果没有，创建一个网站
    public WebSite getWebSiteCategory(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebSite(type));
        }
        return (WebSite)pool.get(type);
    }

    // 获取网站分类的总数（池中有多少网站类型）
    public int getWebSiteConcrete() {
        return pool.size();
    }
}
