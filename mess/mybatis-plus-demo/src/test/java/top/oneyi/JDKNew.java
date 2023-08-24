package top.oneyi;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import org.junit.Test;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.Optional;


public class JDKNew {

    @Test
    public void test01() {
        String str = "123";

        Optional<String> optionalS = Optional.ofNullable(str);
        Optional<Integer> integer = optionalS.map(String::length);
        System.out.println("integer = " + integer.orElse(0));
    }

    private JiebaSegmenter segmenter = new JiebaSegmenter();
    String sentences = "北京京天威科技发展有限公司大庆车务段的装车数量";
    String sentences2 = "我得了阿米巴病";
    String sentences3 = "我得了感冒,总是不想吃饭,我也不想吃药";

    @Test
    public void test02() {
        // 读取 dict 目录下所有的自定义词库**.dict文件。
        WordDictionary.getInstance().init(Paths.get("conf"));
        System.out.println(segmenter.sentenceProcess(sentences));
    }

    @Test
    public void test03() {
        String str = "9";
        BigDecimal a = new BigDecimal(str);
        String str2 = "5";
        BigDecimal a2 = new BigDecimal(str2);
        BigDecimal bigDecimal = a.divide(a2).setScale(0, BigDecimal.ROUND_HALF_UP);
        BigDecimal bigDecimal2 = a.divide(a2).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("bigDecimal = " + bigDecimal);
        System.out.println("bigDecimal2 = " + bigDecimal2);
    }

    @Test
    public void test04() {
        String str = "1";
        BigDecimal a = new BigDecimal(str);
        String str2 = "360";
        BigDecimal a2 = new BigDecimal(str2);
        BigDecimal bigDecimal = new BigDecimal("1000000").multiply(a.divide(new BigDecimal("100"), 4, BigDecimal.ROUND_HALF_UP));
        System.out.println("bigDecimal = " + bigDecimal);
        bigDecimal = bigDecimal.divide(new BigDecimal("360").subtract(new BigDecimal("1")), 4, BigDecimal.ROUND_HALF_UP);
        System.out.println("bigDecimal = " + bigDecimal);
        System.out.println("bigDecimal = " + a.divide(new BigDecimal("100"), 4, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("360").subtract(new BigDecimal("1")), 4, BigDecimal.ROUND_HALF_UP));

        System.out.println("bigDecimal = " + new BigDecimal("1000000").multiply(a.divide(new BigDecimal("100"), 4, BigDecimal.ROUND_HALF_UP)).divide(new BigDecimal("360").subtract(new BigDecimal("1")), 4, BigDecimal.ROUND_HALF_UP));

    }

    @Test
    public void test05() {
        StockGoods stockGoods = new StockGoods();
        // 总重量
        BigDecimal totalWeight = new BigDecimal("1000");
        BigDecimal totalNum = new BigDecimal("100");
        stockGoods.setTotalWeight(totalWeight);
        stockGoods.setTotalNum(totalNum);
        stockGoods.setSgAvailableStockWeight(totalWeight);
        stockGoods.setSgAvailableStockNum(totalNum);
        // 申请仓单重量 和 数量
        BigDecimal weight = new BigDecimal("23");
        System.out.println("扣减重量之前的状态"+stockGoods);
        totalWeight = totalWeight.subtract(weight);
        stockGoods.setSgAvailableStockWeight(totalWeight.subtract(weight),new BigDecimal("10"));
        System.out.println("扣减重量之后的状态"+stockGoods);
        totalWeight = totalWeight.add(weight);
        stockGoods.setSgAvailableStockWeight(totalWeight.subtract(weight),new BigDecimal("10"));
        System.out.println("恢复库存"+stockGoods);
        /*
        生成仓单 传递进来重量 查到在库货物重量和数量

         */

    }


}
