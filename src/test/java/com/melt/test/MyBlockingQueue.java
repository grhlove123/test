package com.melt.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义队列
 * @author melt
 * @create 2018/3/15 10:52
 */
public class MyBlockingQueue<T> {
    private final Object[] items ;
    private int takeIndex ;
    private int putIndex ;
    private int count ;
    private final ReentrantLock lock ;
    private final Condition notEmpty  ;
    private final Condition notFull  ;


    public MyBlockingQueue(int capacity,boolean fair){
        items = new Object[capacity] ;
        lock = new ReentrantLock(fair) ;
        notEmpty = lock.newCondition() ;
        notFull = lock.newCondition() ;
    }

//    public T take() throws InterruptedException {
//        lock.lockInterruptibly();
//        try {
//            while (takeIndex == 0){
//                notFull.await();
//            }
//            items[putIndex++] = bean ;
//            count++ ;
//        } finally {
//            lock.unlock();
//        }
//
//        return null ;
//    }

    public boolean put(T bean) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (putIndex >= items.length -1){
                notFull.await();
                putIndex = 0 ;
            }
            items[putIndex++] = bean ;
            count++ ;
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }

        return true ;
    }

}
