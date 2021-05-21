package com.chixing.day02;
import java.util.Arrays;
public class ArrayDemo {
    public static void main(String[] args) {
        int[] arr1 = new int[6];
        arr1[0] = 10;
        arr1[1] = 20;
        arr1[2] = 30;
        arr1[3] = 40;
        arr1[4] = 50;
        arr1[5] = 60;
        //数组长度
        System.out.println("数组长度：" + arr1.length);

        //遍历数组
        for(int i=0;i<arr1.length;i++){
            System.out.println(arr1[i]);
        }
        //1下标indedx 为零 和2的值  交换位置
        int tenp1=arr1[0];
        arr1[0] = arr1[2];
        arr1[2] = tenp1;
        System.out.println("--------------------------------------------------------");
        int[] arr2 = {23,8,13,56,40,78,99};
        //2找出数组中最大的值
        int max = arr2[0];
        for(int i =1;i <arr2.length;i++){
            if(max < arr2[i]){
                max = arr2[i];
            }
        }
        System.out.println( "最大值为" + max);
        System.out.println("--------------------------------------------------------");
        //3将数组倒置（最后数组中的值是： 23，8，13，56，40,78,99
        /*
        *23 8 13 56 40 78
        *0  1  2  3  4  5
        *
        * */
        for(int i = 0;i<arr2.length/2;i++){
            int qqq=arr2[arr2.length - 1- i];
            arr2[arr2.length - 1- i] = arr2[i];
            arr2[i] = qqq;
        }
        System.out.println(Arrays.toString(arr2));
        System.out.println("22--------------------------------------------------------");

          /*
         （1）定义一个 新数组 b, 将a数组中的元素 复制到 a 数组中

         （2） int[] c = new int[10];
               将a 数组中 前4个元素 复制到 c 数组的最后四个位置上；
              将a 数组中 最后6个元素 复制到 c 前6个位置上

         */
        int[] a = {13,15,67,24,68,36,19,42};
        int[] b = new int[a.length];
        for(int i= 0;i<a.length;i++){
            /*int mm= a[i];
            b[i] = mm;*/
            b[i] = a[i];
        }
        System.out.println(Arrays.toString(b));
        System.out.println("-------------------------------------------------------");
        int[] c = new int[10];
        /*0  1  2  3  4  5  6  7
        * 13 15 67 24 68 36 19 42
        *
        *
        *
        * （2） int[] c = new int[10];
               将a 数组中 前4个元素 复制到 c 数组的最后四个位置上；
              将a 数组中 最后6个元素 复制到 c 前6个位置上
        * */
     for(int i=0;i<a.length;i++){
         if(i >1){
             c[i - 2] = a[i];
         }
     }
     for(int i=0;i<4;i++){
         c[c.length - i - 1]=a[i];
     }
        System.out.println(Arrays.toString(c));

        System.out.println("-------------------------------------------------------");
        int[] aa = {10,47,65,68,71,83,92};
        int ww = 0;
        if(aa.length % 2 ==0){
            ww = (aa[aa.length/2 -1]+ aa[aa.length/2])/2;
        }
        else{
            ww = aa[aa.length/2];
        }
        System.out.println("中位数为： "+ ww);

    }
}
