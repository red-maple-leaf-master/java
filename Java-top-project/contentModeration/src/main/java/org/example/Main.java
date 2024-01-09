package org.example;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import org.junit.Test;

import java.util.List;


public class Main {

    @Test
    public void test_sensitive_word() {
        String text = "小傅哥喜欢烧烤臭毛蛋，豆包爱吃粑粑，如果想吃订购请打电话：13900901878";
        // 判断是否包含敏感词
        System.out.println("SensitiveWordHelper.contains(text) = " + SensitiveWordHelper.contains(text));
        // 返回敏感词
        List<String> wordList = SensitiveWordHelper.findAll(text);
        System.out.println("wordList = " + wordList);
        // 替换敏感词
        String result = SensitiveWordHelper.replace(text);
        System.out.println("result = " + result);
    }
}