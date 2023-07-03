package top.oneyi.demo.BitMap;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;


import java.nio.charset.Charset;

public class BitMapTest {

    /**
     * 使用的是 google的 Guava库
     */
    @Test
    public void test(){
        //  第一个参数为 编码格式  第二个参数为 预计插入的数据量   第三个参数为 误判率
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 100, 0.01);
        // 插入元素
        bloomFilter.put("Lynn");
        bloomFilter.put("666");
        bloomFilter.put("八股文");
        // 判断元素是否存在
        System.out.println(bloomFilter.mightContain("Lynn")); // true
        System.out.println(bloomFilter.mightContain("八股文")); // true
        System.out.println(bloomFilter.mightContain("张三"));  // false
        System.out.println("bloomFilter.toString().getBytes().length = " + bloomFilter.toString().getBytes().length);
        String msg = "Lynn666八股文";
        System.out.println("msg.getBytes().length = " + msg.getBytes().length);
    }
    /** 预计插入的数据 */
    private static Integer expectedInsertions = 10000000;
    /** 误判率 */
    private static Double fpp = 0.01;
    /** 布隆过滤器 */
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), expectedInsertions, fpp);

    @Test
    public void test01(){
        // 插入 1千万数据
        for (int i = 0; i < expectedInsertions; i++) {
            bloomFilter.put(i);
        }

        // 用1千万数据测试误判率
        int count = 0;
        for (int i = expectedInsertions; i < expectedInsertions *2; i++) {
            //
            if (bloomFilter.mightContain(i)) {
                count++;
            }
        }
        System.out.println("一共误判了：" + count);

    }


}
