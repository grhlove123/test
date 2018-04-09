package com.melt.test.sort;

import java.util.Arrays;

/**
 * 选择排序<p>
 * 思想：选择第一个为基准(最小)，如果有比它还小的就交换，这样一趟下来第一个就是最小的了，以此类推
 * @author melt
 * @create 2018/3/19 16:56
 */
public class SelectSort {

    public static void sort(int[] arr){
        for (int i = 0; i< arr.length -1 ;i++){
            for (int j = i+1;j<arr.length ;j++){
                if (arr[i] > arr[j]){
                    //大于则交换
                    int tmp = arr[i] ;
                    arr[i] = arr[j] ;
                    arr[j] = tmp ;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {2,1,10,3,1,5} ;
        sort(arr) ;
        System.out.println(Arrays.toString(arr));
    }
}
