package top.oneyi;


import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
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

    private final static String PATH = "E:\\Desktop\\java_project\\one\\java\\POI_test\\src\\main\\resources\\template\\";

    private final static String sourceFile = "toptest.docx";
    private final static String targetFile = "target.docx";
    private final static String targetPDFFile = "target.pdf";

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
/*                if (document != null) {
                    document.close();
                }*/
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

    /*@Test
    public void test03() throws IOException {
        FileInputStream fileInputStream = null;
        FileOutputStream  fileOutputStream=null;
        try {
            // 读取docx文件
            fileInputStream = new FileInputStream(PATH + targetFile);
            XWPFDocument xwpfDocument = new XWPFDocument(fileInputStream);
            PdfOptions pdfOptions = PdfOptions.create();
            // 输出路径
            fileOutputStream = new FileOutputStream(PATH + targetPDFFile);
            // 调用转换
            PdfConverter.getInstance().convert(xwpfDocument,fileOutputStream,pdfOptions);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileInputStream.close();
            fileOutputStream.close();
        }
    }*/

    @Test
    public void test03(){
        wordToPdf(PATH+targetFile,PATH+targetPDFFile);
    }

    /**
     * word转pdf
     * @param wordPath word的路径
     * @param pdfPath pdf的路径
     */
    public static boolean wordToPdf(String wordPath, String pdfPath){
        boolean result = false;
        try {
            XWPFDocument document=new XWPFDocument(new FileInputStream(new File(wordPath)));
            File outFile=new File(pdfPath);
            outFile.getParentFile().mkdirs();
            OutputStream out=new FileOutputStream(outFile);
            PdfOptions options= PdfOptions.create();
            PdfConverter.getInstance().convert(document,out,options);
            result = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
