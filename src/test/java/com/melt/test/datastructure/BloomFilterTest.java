package com.melt.test.datastructure;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 布隆过滤器
 * @author melt
 * @create 2018/3/31 16:30
 */
public class BloomFilterTest {
    private static int size = 1000000;

    private static BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), size);

    public static void main(String[] args) {
        String url = "www.baidu.com" ;
        long t = System.currentTimeMillis() ;
        for (int i = 0; i < size; i++) {
            bloomFilter.put(url + i);
        }
        System.out.println((System.currentTimeMillis() - t));

        for (int i = 0; i < size; i++) {
            if (!bloomFilter.mightContain(url + i)) {
                System.out.println("有坏人逃脱了");
            }
        }

        List<Integer> list = new ArrayList<Integer>(1000);
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(url + i)) {
                list.add(i);
            }
        }
        System.out.println("有误伤的数量：" + list.size());
    }
}
