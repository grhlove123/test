package com.melt.test.design.adpter;

/**
 * 实现处理适配器
 * @author melt
 * @create 2018/1/16 14:26
 */
public class HttpHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof HttpController;
    }

    @Override
    public void handle(Object handler) {
        ((HttpController)handler).doHttpHandle();
    }
}
