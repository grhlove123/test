package com.melt.test.redis;

import org.junit.After;
import org.junit.Before;
import redis.clients.jedis.Jedis;

/**
 * @author melt
 * @create 2018/1/18 16:54
 */
public class BaseTest {

    protected Jedis jedis ;

    @Before
    public void setUp() throws Exception {
        jedis = new Jedis("127.0.0.1",6379,500);
        jedis.connect();
        jedis.flushAll();
    }

    @After
    public void tearDown() throws Exception {
        jedis.disconnect();
        jedis.close();

    }
}
