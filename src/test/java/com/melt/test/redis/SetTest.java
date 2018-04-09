package com.melt.test.redis;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.HashSet;
import java.util.Set;

/**
 * @author melt
 * @create 2018/2/23 16:47
 */
public class SetTest extends BaseTest{

    @Test
    public void sadd() {
        /**
         * 向set里增加值，返回值为增加值的个数
         */
        long status = jedis.sadd("sadd","1","2") ;
        System.out.println(status);
        status = jedis.sadd("sadd","1","3") ;
        System.out.println(status);
    }

    @Test
    public void smembers() {
        /**
         *  查看key对应的值成员
         */
        jedis.sadd("smembers","1","2") ;
        Set<String> smembers = jedis.smembers("smembers");
        System.out.println(smembers);
    }

    @Test
    public void srem() {
        /**
         * 删除值
         */

        jedis.sadd("srem","1","2") ;
        jedis.srem("srem","1") ;

        System.out.println(jedis.smembers("srem"));

    }

    @Test
    public void spop() {
        /**
         * 随机弹出一个值
         */
        jedis.sadd("spop","1","2") ;
        String spop = jedis.spop("spop");
        System.out.println(spop);

    }

    @Test
    public void smove() {
        /**
         * 把一个值 从一个set移到另一set
         */
        jedis.sadd("smove","1","2") ;
        Long smove = jedis.smove("smove", "dstKey", "1");
        System.out.println(smove);

    }

    @Test
    public void scard() {
        /**
         * 获取成员人数
         */
        jedis.sadd("scard","1","2") ;
        System.out.println(jedis.scard("scard"));
    }

    @Test
    public void sismember() {
        /**
         * 是否存在该值
         */
        jedis.sadd("sismember","1","2") ;
        Boolean sismember = jedis.sismember("sismember", "1");
        System.out.println(sismember);
    }

    @Test
    public void sinter() {
        /**
         * 交集
         */
        jedis.sadd("sinter","1","2") ;
        jedis.sadd("sinter2","1") ;
        Set<String> sinter = jedis.sinter("sinter", "sinter2");
        System.out.println(sinter);

    }

    @Test
    public void sinterstore() {
        /**
         * 交集，把结果存放在第一个参数key里
         */
        jedis.sadd("sinterstore","1","2") ;
        jedis.sadd("sinterstore2","1") ;
        jedis.sinterstore("sinterstore_des","sinterstore","sinterstore2") ;
        System.out.println(jedis.smembers("sinterstore_des"));
    }

    @Test
    public void sunion() {
        /**
         * 并集
         */
        jedis.sadd("sunion","1","2") ;
        jedis.sadd("sunion2","1") ;
        Set<String> sunion = jedis.sunion("sunion3", "sunion", "sunion2");
        System.out.println(sunion);
    }

    @Test
    public void sdiff() {
        /**
         * 以第一个为标准，剔除相同的，返回不一样的
         */
        jedis.sadd("sdiff_key1","1","2","3") ;
        jedis.sadd("sdiff_key2","1","4") ;
        jedis.sadd("sdiff_key3","3") ;
        Set<String> sdiff = jedis.sdiff("sdiff_key1", "sdiff_key2", "sdiff_key3");

        Set<String> expected = new HashSet<>() ;
        expected.add("2") ;
        Assert.assertEquals(expected,sdiff);

    }

    @Test
    public void srandmember() {
        /**
         * 随机返回指定个数的值
         */
        jedis.sadd("srandmember","1","2","3") ;
        System.out.println(jedis.srandmember("srandmember",2));

    }

    @Test
    public void sscan() {

        jedis.sadd("sscan","1","2","3") ;
        ScanResult<String> sscan = jedis.sscan("sscan", ScanParams.SCAN_POINTER_START);
        System.out.println(sscan);
    }

    @Test
    public void sscanMatch() {
        /**
         * 匹配表达式相应的值,个数为N-1
         */
        ScanParams params = new ScanParams();
        params.match("a*");
        params.count(3) ;
        jedis.sadd("sscanMatch","a1","a2","a3","b1") ;
        ScanResult<String> sscanMatch = jedis.sscan("sscanMatch", ScanParams.SCAN_POINTER_START, params);
        System.out.println(sscanMatch.getResult());

    }
}
