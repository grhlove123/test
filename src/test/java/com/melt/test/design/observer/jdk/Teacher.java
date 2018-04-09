package com.melt.test.design.observer.jdk;

import java.util.Observable;

/**
 * @author melt
 * @create 2018/1/16 17:11
 */
public class Teacher extends Observable {

    public void update(String msg){
        setChanged();
        notifyObservers(msg);
    }
}
