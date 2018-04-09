package com.melt.test.design.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * @author melt
 * @create 2018/1/16 17:13
 */
public class Student implements Observer {

    private String name ;
    private Observable observable ;

    public Student(String name,Observable observable) {
        this.name = name;
        this.observable = observable ;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
//        ((Teacher)o).update((String)arg);
        System.out.println("name:" +name +"--" + (String)arg);
    }
}
