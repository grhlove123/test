package com.melt.test.design.observer;

/**
 * 主题
 * @author melt
 * @create 2018/1/16 16:34
 */
public interface Subject {

    boolean addObserver(Observer observer) ;

    boolean removeObserver(Observer observer) ;

    void notifyAllObserver(String msg) ;
}
