package top.oneyi;

import org.apache.poi.xwpf.usermodel.PositionInParagraph;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import top.oneyi.utils.WordUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// 参考博文: https://zhuanlan.zhihu.com/p/150676300
@SpringBootTest
public class MyWord {

    private final static String PATH = "E:\\project\\java\\POI_test\\src\\main\\resources\\template\\";

    private final static String sourceFile = "toptest.docx";
    private final static String targetFile = "target.docx";

    @Test
    public void test01() {

        XWPFDocument document = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(PATH + sourceFile);
            out = new FileOutputStream(PATH + targetFile);
            //获取docx解析对象
            document = new XWPFDocument(in);
            Map<String, String> replaceMap = new HashMap();

            replaceMap.put("name", "张三");
            replaceMap.put("age", "213");
            replaceMap.put("sex", "男");
            replaceMap.put("address", "北京朝阳");
            replaceMap.put("tel", "15560205386");
            WordUtil.changeText(document,replaceMap);

            document.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (document != null) {
                    document.close();
                }
                if (out != null) {
                    out.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test02(){
        String str = "jkljsdjfl${name}dfdsf";
        String value="张三";
        int end = str.lastIndexOf("}");
        int start = str.indexOf("${");
        System.out.println(start);
        System.out.println(end);

       str= str.replace("${name}","张三");
        System.out.println("str = " + str);

    }
}
