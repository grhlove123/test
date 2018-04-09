package com.melt.test.datastructure;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author melt
 * @create 2018/3/20 15:25
 */
public class MyBlockingQueue<T> {

    private final Object[] items ;
    private final ReentrantLock lock ;
    private final Condition notEmpty ;
    private final Condition notFull ;
    private int count ;
    private int takeIndex ;
    private int putIndex ;

    public MyBlockingQueue(int capacity){
        items = new Object[capacity] ;
        lock = new ReentrantLock() ;
        notEmpty = lock.newCondition() ;
        notFull = lock.newCondition() ;
    }

    public boolean put(T bean) throws InterruptedException {
        while (count == items.length)
            notFull.await();

        try {
            lock.lock();
            items[putIndex++] = bean ;
            count++ ;
            if (putIndex == items.length)
                putIndex = 0 ;
            notEmpty.signal();

        } finally {
            lock.unlock();
        }

        return true ;
    }

    public T take() throws InterruptedException {
        while (count == 0){
            notEmpty.await();
        }

        try {
            lock.lockInterruptibly();
            T bean = (T)items[takeIndex] ;
            items[takeIndex] = null ;
            takeIndex++ ;
            count-- ;
            if (takeIndex == items.length)
                takeIndex = 0 ;
            notFull.signal();

        } finally {
            lock.unlock();
        }

        return null ;
    }
}
