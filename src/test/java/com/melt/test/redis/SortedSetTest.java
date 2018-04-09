package com.melt.test.redis;

import org.junit.Test;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.params.sortedset.ZAddParams;

import java.util.Set;

/**
 * @author melt
 * @create 2018/2/24 16:15
 */
public class SortedSetTest extends BaseTest{

    @Test
    public void zadd() {
        jedis.del("zadd") ;
        long zadd = jedis.zadd("zadd", 1, "a");
        System.out.println(zadd);

        zadd = jedis.zadd("zadd", 2, "a", ZAddParams.zAddParams().xx());
        System.out.println(zadd);
        zadd = jedis.zadd("zadd", 3, "a", ZAddParams.zAddParams().nx());
        System.out.println(zadd);
    }

    @Test
    public void zrange() {
        /**
         * 按分数升序
         */
        jedis.zadd("zrang",1d,"b") ;
        jedis.zadd("zrang",2d,"a") ;

        System.out.println(jedis.zrange("zrang",0,1));
    }

    @Test
    public void zrangeByLex() {
        jedis.zadd("foo",1,"a") ;
        jedis.zadd("foo",1,"b") ;
        jedis.zadd("foo",1,"c") ;
        jedis.zadd("foo",1,"d") ;
        /**
         * 按值自然顺序升序，分数必须相同，否则结果不准确，不包括a,包括c
         */
        Set<String> foo = jedis.zrangeByLex("foo", "(a", "[c");
        System.out.println(foo);
    }

    @Test
    public void zrevrangeByLex() {
        jedis.zadd("foo",1,"a") ;
        jedis.zadd("foo",1,"b") ;
        jedis.zadd("foo",1,"c") ;
        jedis.zadd("foo",1,"d") ;
        /**
         * 按值自然顺序降序，分数必须相同，否则结果不准确，不包括a,包括c
         */
        Set<String> foo = jedis.zrevrangeByLex("foo", "[c", "(a");
        System.out.println(foo);
    }

    @Test
    public void zrevrange() {
        /**
         * 按分数降序
         */
        jedis.zadd("foo",1,"a") ;
        jedis.zadd("foo",2,"b") ;
        System.out.println(jedis.zrevrange("foo",0,1));
    }

    @Test
    public void zrem() {
        /**
         * 删除元素
         */
        jedis.zadd("foo",1d,"a") ;
        jedis.zadd("foo",1d,"ab") ;
        jedis.zrem("foo","ab") ;
        System.out.println(jedis.zrange("foo",0,100));
    }

    @Test
    public void zincrby() {
        /**
         * 增加分数
         */
        jedis.zadd("foo",1d,"a") ;
        Double zincrby = jedis.zincrby("foo", 2d, "a");
        System.out.println(zincrby);
    }

    @Test
    public void zrank() {
        /**
         * 元素所在下标
         */
        jedis.zadd("foo",1d,"a") ;
        jedis.zadd("foo",1d,"b") ;
        System.out.println(jedis.zrank("foo","b"));
    }

    @Test
    public void zrevrank() {
        /**
         * 元素所在下标，反序
         */
        jedis.zadd("foo",1d,"a") ;
        jedis.zadd("foo",1d,"b") ;
        System.out.println(jedis.zrevrank("foo","b"));

    }

    @Test
    public void zrangeWithScores() {
        jedis.zadd("foo", 1d, "a");
        jedis.zadd("foo", 10d, "b");
        jedis.zadd("foo", 3d, "c");

        Set<Tuple> foo = jedis.zrangeWithScores("foo", 0, 1);
        System.out.println(foo);


    }

    @Test
    public void zcard() {
        /**
         * 集合的元素个数
         */
        jedis.zadd("foo",1d,"a") ;
        jedis.zadd("foo",1d,"b") ;
        jedis.zadd("foo",1d,"c") ;
        jedis.zadd("foo",2d,"a") ;
        System.out.println(jedis.zcard("foo"));
    }

    @Test
    public void zscore() {
        /**
         * 指定元素的分数
         */

        jedis.zadd("foo",1d,"a") ;
        System.out.println(jedis.zscore("foo","a"));
        System.out.println(jedis.zscore("foo","b"));
    }

    @Test
    public void zcount() {
        /**
         * 指定分数区间的元素个数
         */
        jedis.zadd("foo",1d,"a") ;

        jedis.zadd("foo",2d,"b") ;
        jedis.zadd("foo",3d,"ab") ;
        System.out.println(jedis.zcount("foo",1d,2d));
        /**
         * inf 表示无限大(小)，正负符号
         */
        System.out.println(jedis.zcount("foo","-inf","(4"));
    }

    @Test
    public void zlexcount() {
        /**
         * 指定区间值范围的个数
         */

        jedis.zadd("foo",1d,"a") ;
        jedis.zadd("foo",1d,"ab") ;
        jedis.zadd("foo",1d,"c") ;
        System.out.println(jedis.zlexcount("foo","[a","(b"));
        /**
         * + 表示正无穷大，- 表示无穷小
         */
        System.out.println(jedis.zlexcount("foo","[a","+"));
    }

    @Test
    public void zrangebyscore() {
        /**
         * 分数区间内的元素
         */
        jedis.zadd("foo",1d,"a") ;
        jedis.zadd("foo",2d,"b") ;
        jedis.zadd("foo",3d,"c") ;
        System.out.println(jedis.zrangeByScore("foo",1d,2d));

    }

    @Test
    public void zrangebyscoreWithScores() {
        /**
         * 分数区间内的元素，返回结果不仅包含分数，还有值
         */
        jedis.zadd("foo",1d,"a") ;
        jedis.zadd("foo",2d,"b") ;
        jedis.zadd("foo",3d,"c") ;
        System.out.println(jedis.zrangeByScoreWithScores("foo",1d,2d));

    }

    @Test
    public void zremrangeByRank() {
        /**
         * 删除元素，按分数排序
         */
        jedis.zadd("foo",1d,"a") ;
        jedis.zadd("foo",2d,"b") ;
        jedis.zadd("foo",3d,"c") ;
        jedis.zadd("foo",4d,"d") ;
        System.out.println(jedis.zremrangeByRank("foo",1,2));
        System.out.println(jedis.zrangeByScore("foo",0,10));
    }

    @Test
    public void zunionstore() {
        /**
         * 并集
         */
        jedis.zadd("foo",1d,"a") ;
        jedis.zadd("foo",2d,"b") ;
        jedis.zadd("bar",3d,"a") ;
        jedis.zadd("bar",4d,"d") ;
        System.out.println(jedis.zunionstore("dst","foo","bar"));
    }
}
