package top.oneyi;

import java.util.*;

public class ShellSortDemo {
    public static void main(String[] args) {
        int[] arr = {99, 5, 69, 33, 56, 13, 22, 55, 77, 48, 12, 88, 2, 69, 99};
        System.out.println("排序之前数组：");
        printArray(arr);

        //希尔排序
        insertionSort(arr);

        System.out.println("希尔排序后数组：");
        System.out.println(Arrays.toString(arr));

    }

    private static int[] insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        //希尔排序  升序
        for (int d = arr.length / 2; d > 0; d /= 2) { //d：增量  7   3   1
            System.out.println("增量取值:" + d);
            for (int i = d; i < arr.length; i++) {
                //i:代表即将插入的元素角标，作为每一组比较数据的最后一个元素角标 
                //j:代表与i同一组的数组元素角标
                for (int j = i - d; j >= 0; j -= d) { //在此处-d 为了避免下面数组角标越界   j = j - d
//                    System.out.println("i:" + i + "  j:" + j +"   j+d="+(j+d));
                    if (arr[j] > arr[j + d]) {// j+d 代表即将插入的元素所在的角标
                        //符合条件，插入元素（交换位置）
                        swap(arr, j, j + d);
                    }
                }
            }
            /*测试:此段代码只为查看希尔排序的每次增量变化过程，正常写程序时不要添加星号注释的这部分代码*/
            for (int m = 0; m < arr.length; m++) {
                System.out.print(arr[m] + " ");
            }
            System.out.println("");
            /**/

        }
        return arr;
    }

    /*
   发现无论什么排序。都需要对满足条件的元素进行位置置换。
   所以可以把这部分相同的代码提取出来，单独封装成一个函数。
   */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /*
     * 最近才知道打印数组不用自己写方法这么麻烦，java API中就有 Arrays.toString(arr);
     * 越简单的越容易忽略，这是最后一次自己写这个方法，以后就用自带的方法
     */
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int x = 0; x < arr.length; x++) {
            if (x != arr.length - 1) {
                System.out.print(arr[x] + ", ");
            } else {
                System.out.println(arr[x] + "]");
            }

        }
    }
}
