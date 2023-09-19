package top.oneyi.generator;

import org.junit.Test;
import org.springframework.util.ResourceUtils;
import top.oneyi.generator.utils.OneUtil;

import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/19/17:56
 */
public class test {


    @Test
    public void test01() throws FileNotFoundException {
//        String path = ResourceUtils.getURL("classpath:").getPath();
//        System.out.println("path = " + path);
//        String path1 = test.class.getResource("/").getPath();
//        System.out.println("path1 = " + path1);
        OneUtil.test();
    }
}
