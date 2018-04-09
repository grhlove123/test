package com.melt.test.sort;

import java.util.Arrays;

/**
 * 冒泡排序：<p>
 *     相邻的两个元素进行比较，如果前元素大于后元素，则交换，这样一趟下来最大的沉底，以此类推
 * </p>
 *
 * @author melt
 * @create 2018/3/19 16:31
 */
public class DubbleSort {

    public static void sort(int[] arr){
        int tmp = 0;
        for (int i = 0;i<arr.length;i++){
            for (int j = 0;j<arr.length-1-i;j++){
                if (arr[j] > arr[j+1]) {
                    tmp = arr[j] ;
                    arr[j] = arr[j+1] ;
                    arr[j+1] = tmp ;
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
