package com.melt.test;

/**
 * @author melt
 * @create 2018/3/26 18:12
 */
public class WaitNotifyTest {


    static class AThread extends Thread {
        private Object lock ;

        public AThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println("client组织request");
            synchronized (lock){
                System.out.println("client发出request，正等待结果");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("client收到返回结果");
            }
        }
    }

    static class BThread extends Thread {
        private Object lock ;

        public BThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println("server收到请求，开始处理.....");
            synchronized (lock){
                System.out.println("处理中");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("处理完成,通知client");
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        final Object lock = new Object() ;
        AThread aThread = new AThread(lock) ;
        BThread bThread = new BThread(lock) ;
        aThread.start();
        bThread.start();
    }

}
