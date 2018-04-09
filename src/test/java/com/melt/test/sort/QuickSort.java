package com.melt.test.sort;

import java.util.Arrays;

/**
 * 快速排序<p>
 *     选择一个基准，基准先不移动，比基准小的放左边，比基准大的放右边，直到一趟比较完成后，
 *     再把基准放到它自己的位置
 *     这样一次就完成了，递归实现后续的
 * </p>
 * @author melt
 * @create 2018/3/19 17:53
 */
public class QuickSort {

    public static void sort(int[] array,int low,int high){
        if (high <= low)
            return;
        int tmp ;
        int pos = low ;//基准下标
        int prv = array[pos] ;//基准值
        for (int i=low + 1; i<=high;i++){
            if (prv > array[i]){
                pos++ ;
                tmp = array[pos] ;
                array[pos] = array[i] ;
                array[i] = tmp ;
            }
        }
        //一趟下来后，把基准插入它该有的位置
        tmp = array[pos] ;
        array[pos] = array[low] ;
        array[low] = tmp ;
        //递归
        sort(array,low,pos-1);
        sort(array,pos+1,high);

    }
    public static void main(String[] args) {
        int[] arr = {5,1,6,7,2,4} ;
        sort(arr,0,arr.length-1) ;
        System.out.println(Arrays.toString(arr));
    }

}
