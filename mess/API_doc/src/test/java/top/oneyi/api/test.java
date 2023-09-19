package top.oneyi.generator;


import org.junit.jupiter.api.Test;

import java.io.*;

public class test {

    @Test
    public void test01() {
        String file = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\temp.html";
        // 获取HTML文件流
        StringBuffer htmlSb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), "utf-8"));
            while (br.ready()) {
                htmlSb.append(br.readLine());
            }
            br.close();
            // 删除临时文件
            //file.delete();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // HTML文件字符串
        String htmlStr = htmlSb.toString();
        // 返回经过清洁的html文本
        System.out.println(htmlStr);
    }
}
