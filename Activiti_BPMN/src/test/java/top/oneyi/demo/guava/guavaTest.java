package top.oneyi.demo.guava;

import com.google.common.collect.*;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;
import top.oneyi.pojo.Person;

import java.util.*;

public class guavaTest {
    /**
     * 双键map
     */
    @Test
    public void test01() {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        //存放元素
        Map<String, Integer> workMap = new HashMap<>();
        workMap.put("Jan", 20);
        workMap.put("Feb", 28);
        map.put("Hydra", workMap);

        //取出元素
        Integer dayCount = map.get("Hydra").get("Jan");
        System.out.println("dayCount = " + dayCount);

        Table<String, String, Integer> table = HashBasedTable.create();
        //存放元素
        table.put("Hydra", "Jan", 20);
        table.put("Hydra", "Feb", 28);
        table.put("Hydra", "wrf", 12);

        table.put("Trunks", "Jan", 28);
        table.put("Trunks", "Feb", 16);
        System.out.println("table = " + table);
        //取出元素
        Integer dayCounts = table.get("Hydra", "Feb");
        System.out.println("dayCounts = " + dayCounts);
        Set<String> strings = table.rowKeySet();
        System.out.println(strings);
        Set<String> strings1 = table.columnKeySet();
        System.out.println(strings1);
        System.out.println("table.values() = " + table.values());

        Table<String, String, Integer> transpose = Tables.transpose(table);
        System.out.println(transpose);

        Map<String, Map<String, Integer>> stringMapMap = table.rowMap();
        System.out.println("stringMapMap = " + stringMapMap);
    }

    /**
     * BiMap - 双向Map
     */
    @Test
    public void test02() {
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("Hydra", "Programmer");
        biMap.put("Tony", "IronMan");
        biMap.put("Thanos", "Titan");
        //使用key获取value
        System.out.println(biMap.get("Tony"));

        BiMap<String, String> inverse = biMap.inverse();
        //使用value获取key
        // 反转之后的BiMap并不是一个新的对象 而是一个视图的关联 所以反转之后的操作也会影响到之前的BiMap上
        // 并且由于可以反转 所以值也不能重复
        System.out.println(inverse.get("Titan"));
        inverse.put("ons", "dfd");
        System.out.println("biMap = " + biMap);
    }

    /**
     * 多值map
     */
    @Test
    public void test03() {
        Map<String, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        map.put("day", list);

        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day", 1);
        multimap.put("day", 2);
        multimap.put("day", 8);
        multimap.put("month", 3);
        System.out.println("multimap = " + multimap);
        List<Integer> day = multimap.get("day");
        System.out.println("day = " + day);
        System.out.println("multimap.get(\"er\") = " + multimap.get("er"));

        Map<String, Collection<Integer>> stringCollectionMap = multimap.asMap();
        System.out.println("stringCollectionMap = " + stringCollectionMap);

    }

    /**
     * 范围Map
     */
    @Test
    public void test04() {
        TreeRangeMap<Comparable, Object> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(0, 60), "fail");
        rangeMap.put(Range.closedOpen(60, 90), "satisfactory");
        rangeMap.put(Range.closedOpen(90, 100), "excellent");
        System.out.println(rangeMap.get(59));
        System.out.println(rangeMap.get(60));
        System.out.println(rangeMap.get(90));
        System.out.println(rangeMap.get(91));
    }

    /**
     * 实例Map
     */
    @Test
    public void test05() {
        ClassToInstanceMap<Object> instanceMap = MutableClassToInstanceMap.create();
        Person person = new Person();
        person.setName("dfdfjlk");
        person.setAge(23);

        instanceMap.putInstance(Person.class, person);
        Person instance = instanceMap.getInstance(Person.class);
        System.out.println("instance = " + instance);
    }
}
