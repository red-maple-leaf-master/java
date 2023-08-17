package top.oneyi;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

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
    public void test03(){
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
    public void test04(){
        String str = "9";
        BigDecimal a = new BigDecimal(str);
        String str2 = "360";
        BigDecimal a2 = new BigDecimal(str2);
        BigDecimal bigDecimal = a.divide(a2.subtract(new BigDecimal("1"))).setScale(10, BigDecimal.ROUND_UP );

        System.out.println("bigDecimal = " + bigDecimal);

    }
}
