package top.oneyi.generator.utils;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/19/18:00
 */
public  class OneUtil {

    public static void test() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        System.out.println("path = " + path);
        String path1 = OneUtil.class.getResource("/").getPath();
        System.out.println("path1 = " +path1.replace("target/classes/","").substring(1));
    }
}
