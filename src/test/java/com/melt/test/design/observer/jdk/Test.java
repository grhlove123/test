package com.melt.test.design.observer.jdk;


import java.util.Observable;
import java.util.Observer;

/**
 * @author melt
 * @create 2018/1/16 17:17
 */
public class Test {

    public static void main(String[] args) {
        Teacher observable = new Teacher() ;

        Observer observer = new Student("张三",observable) ;
        Observer observer2 = new Student("李四",observable) ;
        observable.update("hi");

    }
}
