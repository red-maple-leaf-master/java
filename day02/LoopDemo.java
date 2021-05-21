package com.chixing.day02;

public class LoopDemo {
    public static void main(String[] args) {
  /*     *
	    ***
	   *****
	  *******
	 *********  */
        for(int i = 1;i<=5; i++){//控制行数
            for(int j = 1; j<=5-i;j++){//控制空格数 第一次循环 循环四次 依次递减
                System.out.print(" ");
            }
            for(int k =1;k<=2*i-1;k++){//控制*的数量  第一次循环 输出一个   循环四次 依次递增  2*i-1  每次加2个*
                System.out.print("*");
            }
         System.out.println();//换行  行数循环一次 换一次行  换五次
    }
    }
}
