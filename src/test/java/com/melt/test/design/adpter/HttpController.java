package com.melt.test.design.adpter;

/**
 * 不同的处理器，处理逻辑不一样
 * @author melt
 * @create 2018/1/16 14:20
 */
public class HttpController implements Controller  {

    public void doHttpHandle(){
        System.out.println("http request handler");
    }
}
