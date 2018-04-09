package com.melt.test.ConSale;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author melt
 * @create 2018/1/26 11:27
 */
public class Test {

//    private static final JedisPool pool = new JedisPool("127.0.0.1",6379);

    public static void main(String[] args) {
        JedisPool pool = new JedisPool("127.0.0.1",6379);
        String itemId = "item_01" ;
        long count = 6 ;
        Jedis resource = pool.getResource();
        resource.del(itemId) ;
        resource.hincrBy(itemId,"total",count) ;
        resource.hincrBy(itemId,"remaining",count) ;
        resource.close();
        Random random = new Random();

        IntStream intStream = random.ints(1,3) ;
        /**
         * 所以线程同时执行
         */
        CountDownLatch latch = new CountDownLatch(1) ;
        ExecutorService executorService = Executors.newCachedThreadPool();

        int c = 100;
        List<Integer> list = intStream.limit(c).boxed().collect(Collectors.toList()) ;
        for (int i=0;i < c;i++){
            Customer customer = new Customer("user_"+i,list.get(i));
            executorService.execute(new TaskSubmit(itemId, customer,pool,latch)); ;

        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true){
            System.out.println(pool.getNumWaiters() + "---"+ pool.getNumActive());
//            pool.getResource() ;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //开始执行指令
//        latch.countDown();
//        executorService.shutdown();

    }

}
