package com.melt.test.design.adpter;

/**
 * 适配器接口
 * 模拟 springmvc 处理请求
 * @author melt
 * @create 2018/1/16 14:07
 */
public interface HandlerAdapter {

    /**
     * 是否支持本处理器
     * @param handler
     * @return
     */
    boolean supports(Object handler) ;

    /**
     * 处理请求
     * @param handler
     */
    void handle(Object handler) ;
}
