package com.melt.test.ConSale.redisson;

import com.alibaba.fastjson.JSONObject;
import com.melt.test.ConSale.Customer;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

/**
 * @author melt
 * @create 2018/1/25 16:39
 */
public class TaskRdSubmit implements Runnable{

    private String itemId ;

    private Customer customer ;

    private RedissonClient redisson ;

    public TaskRdSubmit(String itemId, Customer customer, RedissonClient redisson) {
        this.itemId = itemId;
        this.customer = customer;
        this.redisson = redisson;
    }

    @Override
    public void run() {

//        RLock lock = redisson.getLock("lock:" + itemId);
//        lock.lock(2, TimeUnit.SECONDS);
        int amount = customer.getAmount() ;
        RMap<String, Long> map =  redisson.getMap(itemId);

        long remaining = map.get("remaining") ;

        if(remaining <= 0 || remaining < amount){
            System.out.println("没有库存了,remaining:" + remaining + "--need:" + amount);
            return ;
        }
        long remaining2 = remaining - amount ;
        map.put("remaining",remaining2) ;

        RList<String> list = redisson.getList(itemId + "_orders");
        list.add(JSONObject.toJSONString(customer)) ;
        System.out.println(customer.getName() + "-- buy "+ amount + " before " + remaining);
//        lock.unlock();

    }
}
