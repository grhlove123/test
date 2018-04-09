package com.melt.test.design.observer;

/**
 * @author melt
 * @create 2018/1/16 16:53
 */
public class Test {

    public static void main(String[] args) {
        Subject subject = new TeacherSubject() ;
        Observer so1 = new StudentObserver("张三",subject) ;
        Observer so2 = new StudentObserver("李四",subject) ;

        subject.notifyAllObserver("hello");
    }
}
