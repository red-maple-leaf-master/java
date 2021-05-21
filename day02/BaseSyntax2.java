package com.chixing.day02;

public class BaseSyntax2 {
    public static void main(String[] args) {
        //就近原则，看等号 = 离谁最近
       int  x = 10;
       int y= x++;
        System.out.println(y );
        int a = 12;
        int b = ++a;
        System.out.println(b);
        x = 10;
        y = 20;
        if(x > y)//一行代码可以不用大括号
            x = 1;
        else
            y = 2;//代码紧跟大括号可以省略

        x = 10;
        y = 20;
        if(x > 9){
            x = 33;//条件满足 x被赋值33；
        }
        y = 30;//y赋值语句  跟上方if语句无关系
        int aa = 2;
        int  bb = 23;
        int cc = 36;
        if(aa > 0 | bb < 10 | cc++ > 23){
            System.out.println("执行语句");
        }else{
            System.out.println("错误！");
        }
        System.out.println(cc);
        System.out.println("------------------------------------------------------------------------");
         aa = 2;
         bb = 23;
         cc = 36;
        if(aa > 0 && bb < 10 && cc++ > 23){
            System.out.println("执行语句");
        }else{
            System.out.println("错误！");
        }
        System.out.println(cc);
        int rrr = 12;
        byte qqq = 10;
        int  cccc = rrr + qqq;
        System.out.println("------------------------------------------------------------");


        System.out.println(5 >> 2 );//101.00-->1.0100--->1.000  小数点后直接取0 左移
        System.out.println(5 << 2 );//101.00---》10100.00  右移
    }



}
