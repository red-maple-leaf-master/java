package com.chixing.day01;
import java.util.Arrays;
import java.sql.SQLOutput;

public class Homework {
    public static void main(String[] args) {
        /*定义两个变量，交换两个变量的值
        int a = 10;
        int b = 20;*/
        int a = 10;
        int b = 20;
        int temp = a;
        a =  b;
        b = temp;
        System.out.println("a=" + a + " " + "b=" + b) ;
/*
        定义一个三位整数变量 x (999=>x>=100)，将其分解出百位、十位、个位，并求出各位之和以及各位之积。
*/
        int x = 456;
        int q = x / 100;
        int w = x % 100 / 10;
        int r = x % 10;
        int sum = q + w + r;
        int ji = q * w * r;
        System.out.println("sum=" + sum + " " + "ji=" + ji );
       /* 给定年份，判断是不是闰年（闰年的条件,能被400整除，或者能被4整除却不能被100整除）
        int year = 2021;*/
        int year = 2021;
        if (year % 400 ==0 || year % 4 ==0 && year % 100 != 0  ){//% 取余    / 整除
            System.out.println("今年为闰年");
        }else{
            System.out.println("今年为平年");
        }
        //*
        //***
        //*****
        //*******
        //*********      */
        for(int i = 1;i < 6;i++){
           for(int j =1;j <= i-1;j++){
               System.out.print("**");
           }
            System.out.println("*");
        }
     /*  *
	    ***
	   *****
	  *******
	 *********  */


        for(int i =1;i<=5;i++ ){
            for(int j = 1;j <=5-i;j++){
            System.out.print(" ");

            }
            for(int k=1;k<=2*i-1;k++){
                System.out.print("*");
            }
            System.out.println("");
        }



/*
        int[] score = {78,56,67,94,73,80,62,90,88,63};
*/
        int[] score = {78,56,67,94,73,80,62,90,88,63};
       for(int i = 0;i<score.length-1;i++){
            for(int j =0;j<score.length-1-i;j++){

                if(score[j] > score[j+1]){
                    int ww =score[j];
                    score[j] = score[j+1];
                    score[j+1] = ww;
                }
            }
        }System.out.print(Arrays.toString(score));

    }
}
