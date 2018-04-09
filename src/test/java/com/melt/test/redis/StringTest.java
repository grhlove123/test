package com.melt.test.redis;

import org.junit.Test;
import redis.clients.jedis.exceptions.JedisDataException;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author melt
 * @create 2018/1/30 17:31
 */
public class StringTest extends BaseTest {

    @Test
    public void setAndGet() {
        String set = jedis.set("string1", "aa");
        System.out.println(set);
        String get = jedis.get("string1");
        System.out.println(get);
    }

    @Test
    public void getSet() {
        /**
         * 返回之前的值
         */
        String s = jedis.getSet("getSet", "b");
        System.out.println(s);
        s = jedis.getSet("getSet", "a");
        System.out.println(s);
    }

    @Test
    public void mget() {
        /**
         * 返回指定的key值，不存在的返回null
         */
        jedis.set("mget","1") ;
        List<String> mget = jedis.mget("mget", "notExist");
        System.out.println(mget);
    }

    @Test
    public void setnx() {
        /**
         * 如果不存在则赋值，否则不操作，可以用于实现分布式锁
         */
        Long setnx = jedis.setnx("setnx", "1");
        System.out.println(setnx);
        setnx = jedis.setnx("setnx", "2");
        System.out.println(setnx);
    }

    @Test
    public void setex() throws InterruptedException {
        /**
         * 设置值并指定有效期,原子性操作
         */
        jedis.setex("setex",2,"1") ;
        System.out.println(jedis.get("setex"));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(jedis.get("setex"));
    }

    @Test
    public void mset() {
        /**
         * 一次性给指定的key赋值，参数成对(key:val),原子性操作
         */
        jedis.mset("a","1","b","2") ;
    }

    @Test
    public void msetnx() {
        /**
         * setnx的多值版本，都不存在则都成功，否则都失败
         */

        long status = jedis.msetnx("msetnx","1","msetnx2","b") ;
        System.out.println(status);
        System.out.println(jedis.get("msetnx"));

        status = jedis.msetnx("msetnx2","1","msetnx3","b") ;
        System.out.println(status);

    }

    @Test(expected = JedisDataException.class)
    public void incrWrongValue() {
        jedis.set("foo", "bar");
        jedis.incr("foo");
    }

    @Test
    public void incr() {
        /**
         * 自增1，返回增加后的值
         */
        long value = jedis.incr("incr") ;

        System.out.println(value);
    }

    @Test
    public void incrBy() {
        /**
         * 自增指定的值
         */

        long val = jedis.incrBy("incrBy",3) ;
        System.out.println(val);
    }

    @Test
    public void incrByFloat(){
        /**
         * 自增浮点型
         */
        double val = jedis.incrByFloat("incrByFloat",2.2) ;
        System.out.println(val);
    }


    @Test
    public void decr() {
        /**
         * 自减
         */
        long val = jedis.decr("decr") ;
        System.out.println(val);
    }

    @Test
    public void append() {
        /**
         * 向指定的key追加字符串，返回值为要追加后的总长度
         */

        long len = jedis.append("append","1") ;
        System.out.println(len);
        len = jedis.append("append","2") ;
        System.out.println(len);

    }

    @Test
    public void strlen() {
        /**
         * 一个key值的字符长度
         */

        long a = jedis.strlen("a");
        System.out.println(a);
        jedis.set("a","this is a string") ;
        a = jedis.strlen("a");
        System.out.println(a);
    }

    @Test
    public void ttl(){
        System.out.println(jedis.ttl("ttl"));
        jedis.set("ttl","ttl") ;
        System.out.println(jedis.ttl("ttl"));
        jedis.setex("ttl",2,"11") ;
        System.out.println(jedis.ttl("ttl"));
        jedis.expire("ttl",1) ;
        System.out.println(jedis.ttl("ttl"));
    }

}
