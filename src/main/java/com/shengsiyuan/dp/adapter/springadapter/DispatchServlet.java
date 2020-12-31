package com.shengsiyuan.dp.adapter.springadapter;

import java.util.ArrayList;
import java.util.List;

public class DispatchServlet {

    public static List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

    public DispatchServlet() {
        handlerAdapters.add(new AnnotationHandlerAdapter());
        handlerAdapters.add(new HttpHandlerAdapter());
        handlerAdapters.add(new SimpleHandlerAdapter());
    }

    public void doDispathch() {
        // 此处模拟SpringMVC从request取handler对象
        // 适配器可以获得到希望的Controller
//        HttpController controller = new HttpController();
        AnnotationController controller = new AnnotationController();
//        SimpleController controller = new SimpleController();
        // 得到对应适配器
        HandlerAdapter adapter = getHandler(controller);
        // 通过适配器执行对应的controller方法
        adapter.handle(controller);
    }

    public HandlerAdapter getHandler(Controller controller) {
        // 遍历：根据得到的controller(handler)，返回对应的适配器
        for (HandlerAdapter adapter : this.handlerAdapters) {
            if (adapter.supports(controller)) {
                return adapter;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        new DispatchServlet().doDispathch();
    }
}
