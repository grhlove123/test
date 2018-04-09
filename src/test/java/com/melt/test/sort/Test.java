package com.melt.test.sort;

import java.util.Arrays;


/**
 * @author melt
 * @create 2018/3/20 14:35
 */
public class Test {

    public static void quickSort(int[] array,int low,int high){
        if (low > high)
            return;
        int pos = low ;
        int prsv = array[low] ;
        for (int i = low +1; i<= high;i++){
            if (prsv > array[i]){
                pos++ ;
                sweep(array,pos,i);
            }
        }

        sweep(array,pos,low);
        quickSort(array,low,pos-1) ;
        quickSort(array,pos+1,high) ;


    }

    private static void sweep(int[] array, int i, int j){
        int tmp = array[j] ;
        array[j] = array[i] ;
        array[i] = tmp ;
    }

    public static void main(String[] args) {
        int[] array = {2,8,3,1,2,6} ;
//        selectSort(array) ;
//        dubbleSort2(array);
        quickSort(array,0,array.length-1) ;
        System.out.println(Arrays.toString(array));

        System.out.println(binSearch(array,0,array.length-1,6));


    }

    private static int binSearch(int[] array,int low,int high,int value){
        if (high >= low){
            int mid = (high + low)/2 ;
            if (array[mid] == value){
                return mid ;
            } else if (array[mid] > value){
                return binSearch(array,low,mid-1,value) ;
            } else {
                return binSearch(array,mid+1,high,value) ;
            }

        }

        return -1 ;
    }





















}
