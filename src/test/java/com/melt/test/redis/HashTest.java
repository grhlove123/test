package com.melt.test.redis;

import org.junit.Test;

import java.util.*;

/**
 * @author melt
 * @create 2018/1/25 13:54
 */
public class HashTest extends BaseTest {

    @Test
    public void hset() {
        long hset = jedis.hset("hset","a","1") ;
        System.out.println(hset);

    }
    @Test
    public void hget(){
        String hget = jedis.hget("hset", "a");
        System.out.println(hget);

    }

    @Test
    public void hsetnx() {
        long a = jedis.hsetnx("hsetnx","a","1") ;
        System.out.println(a);

    }

    @Test
    public void hmset() {
        Map<String,String> map = new HashMap<>(2) ;
        map.put("a","1") ;
        map.put("b","2") ;
        String status = jedis.hmset("hmset", map);
        System.out.println(status);

//        jedis.hmset()
    }

    @Test
    public void hmget() {
//        Map<String,String> map = new HashMap<>(2) ;
        List<String> hmget = jedis.hmget("hmset", "a", "b");
        System.out.println(hmget);
    }

    @Test
    public void hexists() {
        jedis.hset("hexists","a","1") ;
        Boolean hexists = jedis.hexists("hexists", "a");
        System.out.println(hexists);

    }

    @Test
    public void hdel() {
        jedis.hset("hdel","a","1") ;

        Long hdel = jedis.hdel("hdel", "a");
        System.out.println(hdel);

    }

    @Test
    public void hlen() {
        System.out.println(jedis.hlen("hdel"));
    }

    @Test
    public void hkeys() {
        Map<String,String> map = new LinkedHashMap<>() ;
        map.put("a","1") ;
        map.put("b","2") ;
        jedis.hmset("hkeys",map) ;

        Set<String> keys = jedis.hkeys("hkeys") ;
        System.out.println(keys);

    }

    @Test
    public void incrby(){
        Long hincrBy = jedis.hincrBy("incrby", "a", 10);
        System.out.println(hincrBy);
    }
}
