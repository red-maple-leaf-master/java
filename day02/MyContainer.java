package com.chixing.day02;
import java.util.Arrays;
/*
* 该容器能够  无限多个 存放 int 类型的值
 *
 * 本质是  默认长度为0的int数组
 *
 * 动态数组
* */
public class MyContainer {
        int[] defaultArray = new int[10];
        int size =0;
/*
*      添加新元素，若 旧数组已经满了，则创建长度为2n+1新数组 存放新元素
*@param ele 要添加的元素
 * */

    /*    public void add(int ele){
        //1，若数组未满if (size < defaultArray.length)，则添加在数组尾部
            if( size < defaultArray.length){
                defaultArray[size++] = ele;
            }
        // 2    size++
         //   3若数组已满，创建新数组
            else{
                *//*
                *Arrays的copyOf()方法传回的数组是新的数组对象，改变传回数组中的元素值，不会影响原来的数组。
                Arrays.copyOf(数组名 ，数组长度)
                   copyOf()的第二个自变量指定要建立的新数组长度，如果新数组的长度超过原数组的长度，则保留数组默认值，
                *
                * *//*
                defaultArray= Arrays.copyOf(defaultArray,defaultArray.length * 2 - 1);
                defaultArray[size++]=ele;
            }
         // 4 旧数组中的元素复制到新数组，新元素 放到新数组尾部
         // 5 size++
        }*/
    public void add(int ele){
        if (size <defaultArray.length){//当数组元素足够是  添加元素
            defaultArray[size++]=ele;
        }
        else{
            int newArray [] =new int[defaultArray.length *2 + 1];//元素不够的时候  定义一个新数组newArray 并且使其长度变为2n+1
            for(int i =0;i<defaultArray.length;i++){
                newArray[i] = defaultArray[i];//利用for循环进行新旧数组的元素继承
            }
            defaultArray=newArray;//是新旧数组指向同时内存   使其俩个名字都可以使用
            defaultArray[size++]=ele;

        }
    }

    // 遍历数组
    public void show(){
        for(int i =0;i<size;i++){
            System.out.print(defaultArray[i] + " ");
       }
    }


    public static void main(String[] args) {
        MyContainer container = new MyContainer();
        for(int i=99; i>59;i--){
            container.add(i);
        }


        container.show(); //  遍历数组
    }
}