package com.chixing.day02;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] a={12,15,35,5,87,1,6,8,0};

        for(int i=0;i<a.length;i++){
            int index =i;
                for(int j=i+1;j< a.length;j++){
                    if(a[index] > a[j]){
                        index = j;
                    }
                }
                int temp = a[index];
                a[index] = a[i];
                a[i] = temp;
        }
        System.out.println(Arrays.toString(a));
    }
}
