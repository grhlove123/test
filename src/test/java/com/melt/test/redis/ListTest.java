package com.melt.test.redis;

import org.junit.Test;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author melt
 * @create 2018/1/18 16:51
 */
public class ListTest extends BaseTest{

    @Test
    public void lpush(){
        long size = jedis.lpush("list1","1","2") ;
        System.out.println(size);
    }

    @Test
    public void llen(){
        System.out.println(jedis.llen("list1"));
    }

    @Test
    public void llenNotOnList(){
        jedis.set("a1","1") ;
        jedis.llen("a1") ;
    }

    @Test
    public void rpush(){
        long size = jedis.lpush("list1","3") ;
        System.out.println(size);
    }

    @Test
    public void lrange(){
        jedis.lpush("lpush","1") ;
        jedis.lpush("lpush","2") ;
        jedis.lpush("lpush","3") ;
        //下标超了不会报错
        List<String> lrange = jedis.lrange("lpush", 0, 10);
        System.out.println(lrange);

        jedis.rpush("rpush","1","2","3") ;
        lrange = jedis.lrange("rpush", 0, 2);
        System.out.println(lrange);
    }

    @Test
    public void ltrim(){
        jedis.del("ltrim") ;
        jedis.rpush("ltrim","1","2","3") ;
        String status = jedis.ltrim("ltrim", 0, 1);
        List<String> ltrim = jedis.lrange("ltrim", 0, 100);
        System.out.println(ltrim);
    }

    @Test
    public void lset(){
        jedis.del("lset") ;
        jedis.rpush("lset","1","2","3") ;
        jedis.lset("lset",1,"guo") ;
        List<String> expect = Arrays.asList("1","guo","3") ;

        List<String> lrang = jedis.lrange("lset",0,3) ;
        assertEquals(expect,lrang);
        System.out.println(expect);
    }

    @Test
    public void lrem(){
        jedis.del("lrem") ;
        jedis.rpush("lrem","1","2","1","2","3","2") ;
        //从左边开始删除count个数
        jedis.lrem("lrem",2,"2") ;
        System.out.println(jedis.lrange("lrem",0,10));
        jedis.del("lrem") ;
        jedis.rpush("lrem","1","2","1","2","3","2") ;
        //从右边开始删除count个数
        jedis.lrem("lrem",-2,"2") ;
        System.out.println(jedis.lrange("lrem",0,10));
    }

    @Test
    public void lpop(){
        jedis.del("lpop") ;
        jedis.rpush("lpop","1","2","1","2","3","2") ;
        jedis.lpop("lpop") ;
        System.out.println(jedis.lrange("lpop",0,10));
    }

    @Test
    public void rpop(){
        jedis.del("rpop") ;
        jedis.rpush("rpop","1","2","1","2","3","2") ;
        jedis.rpop("rpop") ;
        System.out.println(jedis.lrange("rpop",0,10));
    }

    @Test
    public void rpoplpush(){
        jedis.del("rpoplpush") ;
        jedis.rpush("rpoplpush","1","2","1","2","3","2") ;

        jedis.del("rpoplpush2") ;
        jedis.rpush("rpoplpush2","a","b") ;

        String rpoplpush = jedis.rpoplpush("rpoplpush", "rpoplpush2");
        System.out.println(rpoplpush);

        System.out.println(jedis.lrange("rpoplpush",0,10));

        System.out.println(jedis.lrange("rpoplpush2",0,10));
    }

    @Test
    public void blpop(){
        jedis.del("blpop") ;
        new Thread(() ->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("inset data");

            jedis.rpush("blpop","1","2","3") ;
        }).start();

        /**
         * 执行lpop,如果有数据，立即返回
         * 否则阻塞timeout秒
         */
        Jedis jedis2 = new Jedis("127.0.0.1",6379,500);
        jedis2.connect();
        List<String> blpop = jedis2.blpop(6, "blpop");
        System.out.println(blpop);
    }

    @Test
    public void rpushx(){
        jedis.lpush("rpushx","a") ;
        /**
         * 只有存在key时才操作，否则报错
         */
        long status = jedis.rpushx("rpushx","1") ;
        System.out.println(status);
    }

    @Test
    public void linsert(){
        /**
         * 在列表指定元素前/后 插入元素
         */
        long status = jedis.linsert("linsert", BinaryClient.LIST_POSITION.BEFORE,"a","b") ;
        System.out.println(status);

        jedis.rpush("linsert","a","b","c") ;
        status = jedis.linsert("linsert", BinaryClient.LIST_POSITION.BEFORE,"a","1") ;
        System.out.println(status);
        System.out.println(jedis.lrange("linsert",0,-1));

        status = jedis.linsert("linsert", BinaryClient.LIST_POSITION.AFTER,"c","1") ;
        System.out.println(status);
        System.out.println(jedis.lrange("linsert",0,-1));
    }

}
