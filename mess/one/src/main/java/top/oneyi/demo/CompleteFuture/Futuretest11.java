package top.oneyi.demo.CompleteFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 异步方法练习
 *
 * @author oneyi
 * @date 2023/2/8
 */

public class Futuretest11 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        thenApplyTest();
//        handleTest();
//        thenAccept();
//        thenRunTest();
//        thenCombineTest();
//        thenAcceptBoth();
//        applyToEitherTest();


        long end = System.currentTimeMillis();
        System.out.println("一共耗时" + (end - start));

    }

    /**
     * 俩个方法 哪个快我就用哪个去的结果作为转化对象
     */
    private static void applyToEitherTest() {
        StringBuilder str = new StringBuilder();

        CompletableFuture<StringBuilder> f1 = CompletableFuture.supplyAsync(new Supplier<StringBuilder>() {
            @Override
            public StringBuilder get() {
                StringBuilder str1 = new StringBuilder();
                try {
                    Thread.sleep(300);
                    str1 = str;
                    str1.append("我最快1");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return str1;
            }
        });
        CompletableFuture<StringBuilder> f2 = CompletableFuture.supplyAsync(new Supplier<StringBuilder>() {
            @Override
            public StringBuilder get() {
                StringBuilder str2 = new StringBuilder();
                try {
                    str2 = str;
                    Thread.sleep(200);
                    str2.append("我最快2");

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return str2;
            }
        });

        CompletableFuture<StringBuilder> result = f1.applyToEither(f2, new Function<StringBuilder, StringBuilder>() {
            @Override
            public StringBuilder apply(StringBuilder t) {
                System.out.println(t);
                return t;
            }
        });

        System.out.println(result.join());
    }

    /**
     * 俩个异步任务处理完成之后,把结果一块交给thenAcceptBoth处理   无返回值
     */
    private static void thenAcceptBoth() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1=" + t);
                return t;
            }
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(t);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2=" + t);
                return t;
            }
        });
        f1.thenAcceptBoth(f2, new BiConsumer<Integer, Integer>() {
            @Override
            public void accept(Integer t, Integer u) {
                System.out.println("f1=" + t + ";f2=" + u + ";");
                System.out.println(t + u);
            }
        });
        System.out.println("f1.join() = " + f1.join());
        System.out.println("f2.join() = " + f2.join());
    }

    /**
     * 合并任务  有返回值
     */
    private static void thenCombineTest() {
        StringBuilder str = new StringBuilder();
        str.append("初始数据");
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            str.append("->>>>>>第一个处理数据");
            System.out.println(str);
            return "hhhhh";
        });
        CompletableFuture<String> uCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            str.append("->>>>>>第二个处理数据");
            System.out.println(str);
            return "aaaaaaa";
        });
        // 合并俩个任务的返回值,因为俩个流处理外部的变量会影响到外面的变量,所以尽量使用内部新建变量,重新赋值
        CompletableFuture<String> stringBuilderCompletableFuture = uCompletableFuture.thenCombine(uCompletableFuture2, (s, u) -> {

            return s + " " + u;
        });
        System.out.println("uCompletableFuture.join() = " + uCompletableFuture.join());
        System.out.println("uCompletableFuture2.join() = " + uCompletableFuture2.join());
        System.out.println("stringBuilderCompletableFuture = " + stringBuilderCompletableFuture.join());
    }

    private static void thenRunTest() {
        StringBuilder str = new StringBuilder();
        str.append("初始数据");
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {

            try {
                Thread.sleep(100);
                int i = 1 / 0;
                // 这里抛异常 没处理数据,但是后面处理数据成功
                str.append("-->1处理数据");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }).thenRun(() -> {
            // 不关心上面任务处理结果,只要上面任务处理完成就开始执行thenRun
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            str.append("--->2处理数据");
        });
        System.out.println("voidCompletableFuture.join() = " + voidCompletableFuture.join());
        System.out.println("str = " + str);
    }

    /**
     * 也是串行处理数据 不过没有返回值  不影响最初的数据
     */
    private static void thenAccept() {
        String str = "第一次";
        CompletableFuture<Void> thenApply = CompletableFuture.supplyAsync(() -> {
            System.out.println("我是异步处理不反悔");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return str + "处理完毕";
        }).thenAccept(s -> {
            // s 是上面传递过来的数据
            System.out.println("s --> " + s);
            // 最初的数据不影响
            System.out.println(str);
        });
        CompletableFuture<Void> thenApply2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("我是异步处理不反悔!!!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return str + "处理完毕!!";
        }).thenAccept(s -> {
            // s 是上面传递过来的数据
            System.out.println("s --> " + s);
            // 最初的数据不影响
            System.out.println(str);
        });
        System.out.println(str);
        System.out.println(thenApply.join());
        System.out.println(thenApply2.join());
    }

    /**
     * 任务完成之后在执行也能执行异常的任务
     */
    private static void handleTest() {

        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> {
            // 抛出异常返回未空值
            int i = 10 / 0;

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

    /**
     * 串行处理数据 防止异步处理数据紊乱
     */
    private static void thenApplyTest() {
        // 只能执行正常的任务,任务出现异常则不执行thenApply方法
        StringBuilder str = new StringBuilder();
        // 有四种方式创建 异步操作  supplyAsync 和 runAsync 第一个有返回值,第二个没有返回值 各有俩个重载方法
        CompletableFuture<StringBuilder> cf = CompletableFuture.supplyAsync(() -> {
            str.append("hello");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return str;
        }).thenApply((s) -> {
            s.append(" world");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        });
        // thenApply方法 主要是跟踪单独线程的数据处理,方式多线程异步导致数据错乱,就是串行处理数据
        StringBuilder str2 = new StringBuilder();
        CompletableFuture<StringBuilder> cf2 = CompletableFuture.supplyAsync(() -> {
            str2.append("hello");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return str2;
        }).thenApply((s) -> {
            s.append(" world !!!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s;
        });
        // 对于外部数据直接操作 影响外部数据
        System.out.println("str = " + str);
        System.out.println("str2 = " + str2);
        System.out.println("cf.join() = " + cf.join());
        System.out.println("cf2.join() = " + cf2.join());
    }

}
