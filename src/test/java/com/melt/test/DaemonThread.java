package com.melt.test;

import java.io.IOException;

/**
 * @author melt
 * @create 2018/2/19 17:21
 */
public class DaemonThread {

    public static void execute(){
        for (int i = 0; i<10; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(()->execute()) ;
        thread.setDaemon(true);
        thread.start();
        System.in.read() ;
    }
}
