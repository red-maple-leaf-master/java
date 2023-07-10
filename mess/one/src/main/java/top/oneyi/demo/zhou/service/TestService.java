package top.oneyi.demo.zhou.service;


import top.oneyi.demo.zhou.pojo.ElectricityData;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author oneyi
 * @date 2023/2/10
 */

public class TestService {


    public static void main(String[] args) {
        // 数据准备
        ElectricityData[] arr01 = {new ElectricityData("1", "10"), new ElectricityData("4", "10"), new ElectricityData("6", "10")};
        ElectricityData[] arr02 = {new ElectricityData("2", "10"), new ElectricityData("4", "10"), new ElectricityData("1", "10"), new ElectricityData("9", "10")};
        // Arrays.aslist(arr01) 创建的对象是 ArrayList的内部类而不是 ArrayList 内部类也是实现了 AbstractList 这个抽象类  这个抽象类的add 和 remove 都是默认抛出 UnsupportedOperationException 异常
        List<ElectricityData> strArrays = Arrays.asList(arr01);
        strArrays.remove(new ElectricityData("1", "10"));


        List<ElectricityData> list01 = new ArrayList<>(Arrays.asList(arr01));
        List<ElectricityData> list02 = new ArrayList<>(Arrays.asList(arr02));

        // 合并集合
        list01.addAll(list02);
        System.out.println("list01 = " + list01);
        // 循环次数  和集合长度一致
        int count = 0;
        // 创建返回值 map
        Map<String, Integer> maps = new HashMap<>();
        for (ElectricityData electricityData : list01) {
            String mouth = electricityData.getMouth();
            Integer num = Integer.valueOf(electricityData.getNum());
            // 如果 map中有该key 说明不是第一次存入 取得map的数量加上 对象的数量
            if (maps.containsKey(mouth)) {
                maps.put(mouth, maps.get(mouth) + num);
            } else {
                // 否则第一次存储 直接设置
                maps.put(mouth, num);
            }
            count++;
        }
        System.out.println("maps = " + maps);
        System.out.println("count = " + count);
        System.out.println("===================================================");
        List<ElectricityData> collect = new ArrayList<>(list01.stream().collect(Collectors.toMap(
                ElectricityData::getMouth, w -> w, (o1, o2) -> {
                    int res = Integer.parseInt(o1.getNum()) + Integer.parseInt(o2.getNum());
                    o1.setNum(Integer.toString(res));
                    return o1;
                }
        )).values());
        System.out.println("collect = " + collect);
    }
}
