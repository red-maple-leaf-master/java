package top.oneyi.demo.CompleteFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

/**
 * 异步方法练习
 * @author oneyi
 * @date 2023/2/8
 */

public class Futuretest11 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        thenApplyTest();
//        handleTest();
        long end = System.currentTimeMillis();
        System.out.println("一共耗时"+(end - start));

    }

    /**
     *  任务完成之后在执行也能执行异常的任务
     */
    private static void handleTest() {

        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> {
           // 抛出异常返回未空值
            int i= 10 / 0;

            try {
                Thread.sleep(200);
                 i = new Random().nextInt(10);
                System.out.println("i = " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return i;
        }).handle(((integer, throwable) -> {
            try {
                Thread.sleep(400);
                System.out.println(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return integer;
        }));

        System.out.println("handle.join() = " + handle.join());
    }

    private static void thenApplyTest() {
        // 只能执行正常的任务,任务出现异常则不执行thenApply方法
        StringBuilder str=new StringBuilder();
        // 有四种方式创建 异步操作  supplyAsync 和 runAsync 第一个有返回值,第二个没有返回值 各有俩个重载方法
        CompletableFuture<StringBuilder> cf = CompletableFuture.supplyAsync(() -> {
            str.append("hello");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return str;
        }).thenApply((s)->{
            s.append(" world");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        });
        // thenApply方法 主要是跟踪单独线程的数据处理,方式多线程异步导致数据错乱,就是串行处理数据
        StringBuilder str2=new StringBuilder();
        CompletableFuture<StringBuilder> cf2 = CompletableFuture.supplyAsync(() -> {
            str2.append("hello");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return str2;
        }).thenApply((s)->{
            s.append(" world !!!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        });

        System.out.println("cf.join() = " + cf.join());
        System.out.println("cf2.join() = " + cf2.join());
    }

}
