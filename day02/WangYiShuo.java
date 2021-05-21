package com.chixing.day02;

public class WangYiShuo {
    public static void main (String arg[]){
        int i,j,k,n;
        for(i=1;i<=5;i++) {
            for (j = 1; j <= 5 - i; j++) {
                System.out.print(" ");
            }
            for (k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }

            System.out.println(" ");
        }
        for(i=1;i<=5;i++){
            for(j=1;j<=2*i-1;j++){
                System.out.print("*");
            }
            System.out.println();
        }

    }
}
