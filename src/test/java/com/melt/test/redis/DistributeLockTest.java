package com.melt.test.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author melt
 * @create 2018/3/7 18:21
 */
public class DistributeLockTest /*extends BaseTest*/ {

//    @Test
//    public void acquire(){
//        boolean b = DistributeLock.tryGetDistributedLock(jedis, "lockA", "requestA", 10);
//        System.out.println(b);
//        b = DistributeLock.tryGetDistributedLock(jedis, "lockA", "requestA", 10);
//        System.out.println(b);
//    }

    public static void main(String[] args) {

        JedisPool pool = new JedisPool("127.0.0.1",6379);
        String itemId = "item_01" ;
//        long count = 6 ;
        Jedis resource = pool.getResource();
        resource.del(itemId) ;
        /**
         * 指定库存
         */
        resource.incrBy(itemId,20) ;
        resource.close();

        /**
         * 所以线程同时执行
         */
        CountDownLatch latch = new CountDownLatch(1) ;
        ExecutorService executorService = Executors.newCachedThreadPool();

        Random random = new Random();
        /**
         * 买1-3个
         */
        IntStream intStream = random.ints(1,3) ;
        int c = 100;
        List<Integer> list = intStream.limit(c).boxed().collect(Collectors.toList()) ;
        for (int i=0;i < c;i++){
//            Customer customer = new Customer("user_"+i,list.get(i));
            executorService.execute(new Task(itemId,"user_"+i,pool,latch,list.get(i))); ;

        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();

        while (true){
            Jedis jedis = pool.getResource() ;
            try {
                Thread.sleep(2000);
                System.out.println(jedis.get(itemId));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                jedis.close();
            }
        }
    }

    static class Task implements Runnable{
        private String itemId ;
        private String requestId;
        private JedisPool pool;
        private CountDownLatch latch ;
        private Integer count ;

        public Task(String itemId, String requestId, JedisPool pool, CountDownLatch latch,Integer count) {
            this.itemId = itemId;
            this.requestId = requestId;
            this.pool = pool;
            this.latch = latch;
            this.count = count ;
        }

        @Override
        public void run() {
            Jedis jedis = null ;
            String lock = itemId + "_lock" ;
            try {
                latch.await();
                jedis = pool.getResource() ;
                lock = itemId + "_lock" ;
                //获取锁
                while (!DistributeLock.tryGetDistributedLock(jedis, lock, requestId, 50)){
                    Thread.sleep(10);
                }
                System.out.printf("%s获取得到锁\n",requestId);

                int remaing = Integer.parseInt(jedis.get(itemId)) ;

                if(remaing < count){
                    System.out.println("库存不足！！");
                }
                if(remaing <= 0) {
                    System.out.println("售磬了！！");
                    return;
                }
                /**
                 * 加锁同时加上watch，保证了不会因为执行时间过长，锁失效而导致不一致问题
                 */
                jedis.watch(lock) ;
                Transaction t = jedis.multi();
                //生成订单。。。
                t.rpush("order_"+itemId,requestId) ;
                //减库存
                t.incrBy(itemId,-count) ;
                t.exec() ;
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if(DistributeLock.releaseDistributedLock(jedis,lock, requestId)){
                    System.out.printf("%s购买数量%s成功\n",requestId,String.valueOf(count));
                }
                jedis.close();
            }

        }
    }

}
