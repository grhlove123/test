package com.melt.test.ConSale;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author melt
 * @create 2018/1/25 16:39
 */
public class TaskSubmit implements Runnable{

    private String itemId ;

    private Customer customer ;

    private JedisPool pool ;

    private CountDownLatch latch ;

    public TaskSubmit(String itemId, Customer customer, JedisPool pool,CountDownLatch latch) {
        this.itemId = itemId;
        this.customer = customer;
        this.pool = pool;
        this.latch = latch ;
    }

    @Override
    public void run() {
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Jedis jedis = null ;
        try {
            jedis = pool.getResource() ;
        for (int i = 0;i < 3;i++){
//            int i = 0 ;
            System.out.printf("用户：%s \r\n",customer.getName() + "--"+ i+ "--start");

            System.out.printf("用户：%s \r\n",customer.getName() + "--"+ i+"--end");
            int amount = customer.getAmount() ;
            jedis.watch(itemId) ;
            String remaiStr = jedis.hget(itemId,"remaining") ;
            if (i > 0){
                System.out.printf("用户：%s 需要：%s，但是库存：%s重试：%s\r\n",customer.getName(),amount,remaiStr,i);
            }

            long remaining = Long.parseLong(remaiStr) ;
            if(remaining <= 0 || remaining < amount){
                System.out.printf("用户：%s 需要：%s，但是库存：%s\r\n",
                        customer.getName(),amount,remaining);
//            System.out.println("没有库存了");
                return ;
            }

            Transaction t = jedis.multi();
            //减库存
//            Response<Long> responseRemai =
            t.hincrBy(itemId, "remaining", -amount);
            //放入到队列
            t.rpush(itemId + "_orders", JSONObject.toJSONString(customer)) ;
            List<Object> resp = t.exec() ;
            System.out.printf("close用户：%s \r\n",customer.getName() + "--"+ i+ "--start");
//
            System.out.printf("close用户：%s \r\n",customer.getName() + "--"+ i+ "--end");
//            if (resp == null || resp.isEmpty()){
//                continue;
//            }
//            jedis.close();
            if (resp ==null || resp.isEmpty() || (Long)resp.get(0) == remaining - amount){
                System.out.println(customer.getName() + "-- buy "+ amount + " before " + remaining);
               return;
            }

        }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }






    }
}
