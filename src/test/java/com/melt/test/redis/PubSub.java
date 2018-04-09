package com.melt.test.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @author melt
 * @create 2018/2/27 13:17
 */
public class PubSub extends BaseTest{

    private void publishOne(final String channel,final String message){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Jedis jedis = new Jedis("127.0.0.1",6379,500);
                jedis.connect();
                jedis.publish(channel,message) ;
                jedis.disconnect();
            }
        }).start();
    }

    @Test
    public void subscribe(){
        jedis.subscribe(new JedisPubSub() {

            @Override
            public void onMessage(String channel, String message) {
                System.out.println("onMessage:" + message);
                unsubscribe();
            }

            @Override
            public void onUnsubscribe(String channel, int subscribedChannels) {
                System.out.println("onUnsubscribe");
            }

            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                System.out.println("onSubscribe");
                System.out.printf("channel:[%s], subscribedChannels:[%s]\t\r",channel,subscribedChannels);
                publishOne("foo", "exit");
            }
        },"foo");
    }
}
