package com.melt.test.design.observer;

/**
 * @author melt
 * @create 2018/1/16 16:44
 */
public class StudentObserver implements Observer {

    private String name ;
    private Subject subject ;

    public StudentObserver(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
        subject.addObserver(this) ;
    }

    @Override
    public void change(String msg) {
        System.out.println(name + " 收到：" + msg);
    }
}
