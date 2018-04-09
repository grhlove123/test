package com.melt.test.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author melt
 * @create 2018/1/16 16:38
 */
public class TeacherSubject implements Subject {
    private final List<Observer> list  ;

    public TeacherSubject() {
        list = new ArrayList<>();
    }

    @Override
    public boolean addObserver(Observer observer) {
        list.add(observer) ;
        return false;
    }

    @Override
    public boolean removeObserver(Observer observer) {
        return false;
    }

    @Override
    public void notifyAllObserver(String msg) {
        list.forEach(p -> {
            p.change(msg);
        });
    }
}
