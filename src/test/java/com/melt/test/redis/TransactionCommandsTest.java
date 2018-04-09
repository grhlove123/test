package com.melt.test.redis;

import org.junit.Test;
import redis.clients.jedis.Transaction;

import java.util.Random;

/**
 * @author melt
 * @create 2018/1/30 17:24
 */
public class TransactionCommandsTest extends BaseTest {

    @Test
    public void watch(){
        jedis.set("lock","11") ;

        jedis.watch("lock") ;
        Transaction t = jedis.multi() ;
        /**
         * 开启事务后，本客户端可以修改key的值，如果是别的客户端修改则丢弃事务
         */
        t.set("lock","22") ;
        t.exec() ;
        System.out.println(jedis.get("lock"));

    }

    /**
     * 模拟订单提交
     */
    @Test
    public void order(){
        String lockKey = "items01lockKey" ;
        jedis.del(lockKey) ;
        jedis.incrBy("item1",100) ;
        /**
         * 1.加分布式锁
         */

        boolean flg = DistributeLock.tryGetDistributedLock(jedis,lockKey,"request1",50) ;
        if (flg){
            try {
                //加锁同时加上watch，保证了不会因为执行时间过长，锁失效而导致不一致问题
                jedis.watch(lockKey,"item1") ;
                Transaction t = jedis.multi();
                //减库存
                t.incrBy("item1",-1) ;
                //生成订单。。。
                if((new Random()).nextBoolean() == true){
                    t.exec() ;
                    System.out.println("执行成功");
                } else {
                    System.out.println("生成订单失败");
                    t.discard() ;

                }

            } finally {
                System.out.println(jedis.incrBy("item1",0));
                DistributeLock.releaseDistributedLock(jedis,lockKey,"request1") ;
            }
        }



    }
}
