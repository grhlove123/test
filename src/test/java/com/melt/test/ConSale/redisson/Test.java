package com.melt.test.ConSale.redisson;

import com.melt.test.ConSale.Customer;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author melt
 * @create 2018/1/26 11:27
 */
public class Test {


    public static void main(String[] args) {
        // connects to 127.0.0.1:6379 by default
        RedissonClient redisson = Redisson.create();

        String itemId = "item_02" ;
        RMap<String, Long> map =  redisson.getMap(itemId);
        long count = 100 ;
        map.put("remaining",count) ;
        redisson.getList(itemId + "_orders").readAll() ;
        Random random = new Random();
        IntStream intStream = random.ints(1,6) ;
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        int c = 10000;
        List<Integer> list = intStream.limit(c).boxed().collect(Collectors.toList()) ;
        for (int i=0;i < c;i++){
            Customer customer = new Customer("user_"+i,list.get(i));
            executorService.execute(new TaskRdSubmit(itemId, customer,redisson)); ;

        }

    }

}
