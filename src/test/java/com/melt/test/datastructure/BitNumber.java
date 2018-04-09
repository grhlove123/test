package com.melt.test.datastructure;

/**
 * @author melt
 * @create 2018/3/9 10:37
 */
public class BitNumber {

    public static void main(String[] args) {
        int[] array = new int[4];
        for (int i = 0; i < array.length; i++) {
            array[i] = 16;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] >> 4;
            System.out.println(array[i]);
        }

        /**
         *  特定位设计与清除
         */
        int a = 0;
        a |= (1<<5);     // | 按位或操作 ,双目运算符 a = a|(1<<5);
        System.out.println(a);
        a &= ~(1<<5);    // & 按位与操作，双目运算符， ~ 按位非操作，单目运算符
        System.out.println(a);

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }
}
