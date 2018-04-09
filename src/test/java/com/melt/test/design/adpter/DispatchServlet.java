package com.melt.test.design.adpter;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟springmvc 入口servlet
 * @author melt
 * @create 2018/1/16 14:43
 */
public class DispatchServlet {
    private List<HandlerAdapter> adapterList = new ArrayList<>() ;

    public DispatchServlet() {
        adapterList.add(new SimpleHandlerAdapter()) ;
        adapterList.add(new HttpHandlerAdapter()) ;
        adapterList.add(new AnnoHandlerAdapter()) ;
    }

    public void doDispatch(){
        //模拟不同的controller
//        Controller controller = new SimpleController() ;
//        Controller controller = new HttpController() ;
        Controller controller = new AnnotationController() ;
        HandlerAdapter handler = getHandler(controller) ;
        handler.handle(controller);

    }

    public HandlerAdapter getHandler(Controller controller){

        return adapterList.stream().filter(a -> a.supports(controller)).findFirst().get() ;

    }

    public static void main(String[] args) {
        new DispatchServlet().doDispatch();
    }
}
