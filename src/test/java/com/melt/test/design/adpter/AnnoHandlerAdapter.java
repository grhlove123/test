package com.melt.test.design.adpter;

/**
 * 实现处理适配器
 * @author melt
 * @create 2018/1/16 14:26
 */
public class AnnoHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof AnnotationController;
    }

    @Override
    public void handle(Object handler) {
        ((AnnotationController)handler).doAnnoHandle();
    }
}
