package top.oneyi.demo.CompleteFuture;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * CompletableFuture练习
 *
 * @author oneyi
 * @date 2023/2/8
 */

public class FutureTest10 {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        List<String> list = new ArrayList<>();
        CompletableFuture<List<String>> w1 = CompletableFuture.supplyAsync(() -> {
            list.add("1");
            System.out.println("第一个任务完成");
            try {
                Thread.sleep(200);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return list;
        });
        CompletableFuture<List<String>> w2 = CompletableFuture.supplyAsync(() -> {
            list.add("2");
            System.out.println("第二个任务完成");
            try {
                Thread.sleep(400);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二个任务完成");
            return list;
        });

        CompletableFuture.anyOf(w1, w2);
        System.out.println("list = " + list);
        long end = System.currentTimeMillis();

        System.out.println("一共耗时" + (end - start));

        System.out.println("=========================================================");

        CompletableFuture<List<String>> painting = CompletableFuture.supplyAsync(() -> {
            // 第一个任务获取美术课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            stuff.add("画笔");
            stuff.add("颜料");
            try {
                Thread.sleep(400);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return stuff;
        });
        CompletableFuture<List<String>> handWork = CompletableFuture.supplyAsync(() -> {
            // 第二个任务获取劳技课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            stuff.add("剪刀");
            stuff.add("折纸");
            try {
                Thread.sleep(400);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return stuff;
        });
        CompletableFuture<List<String>> total = painting
                // 传入handWork列表，然后得到两个CompletableFuture的参数Stuff1和2
                .thenCombine(handWork, (stuff1, stuff2) -> {
                    // 合并成新的list
                    List<String> totalStuff = Stream.of(stuff1, stuff1)
                            .flatMap(Collection::stream)
                            .collect(Collectors.toList());
                    return totalStuff;
                });
        System.out.println(JSONObject.toJSONString(total.join()));

        long end1 = System.currentTimeMillis();

        System.out.println("一共耗时" + (end1 - start));

    }
}