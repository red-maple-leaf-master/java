package top.oneyi.generator;


//import fr.opensagres.odfdom.converter.core.BasicURIResolver;


//import fr.opensagres.odfdom.converter.xhtml.XHTMLConverter;

import fr.opensagres.poi.xwpf.converter.core.BasicURIResolver;
import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.oneyi.generator.common.utils.WordToHtmlUtil;


import javax.lang.model.element.Element;
import javax.lang.model.util.Elements;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;


@SpringBootTest//加载 SpringBoot 启动类
public class wordTest {


    @Test
    public void test01() throws IOException {


        //存放图片的目的地址（）
        String imagePath = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\";
        //需要被转换的docx文件
        String sourceFileName = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\temp.docx";
        //转换成的html文件(不存在将会被创建，存在会被覆盖)
        String targetFileName = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\temp.html";
        OutputStreamWriter outputStreamWriter = null;
        try {
            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
            XHTMLOptions options = XHTMLOptions.create();
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);

            // 存放图片的文件夹
            options.setExtractor(new FileImageExtractor(new File(imagePath)));
            // html中图片的路径
            options.URIResolver(new BasicURIResolver("image"));
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
            xhtmlConverter.convert(document, outputStreamWriter, options);
        } finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
        }


    }


    // outputStream转inputStream
    public static ByteArrayInputStream parse(OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    @Test
    public void test02() throws Exception {


   /*     docx("E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\",
               "temp.docx","temp01.html" );*/


        WordToHtmlUtil.docx("E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\",
                "temp.docx", "temp02.html");
    }


    // https://blog.csdn.net/yuxielea/article/details/103477874

    /**
     * @Author: jimmy
     * @Date: 2019/12/10 12:34
     * @Param: [filePath, fileName, htmlName]
     * @Return: void
     * @Description: 转换docx
     */
    public static void docx(String filePath, String fileName, String htmlName) throws Exception {
        final String filePathName = filePath + fileName;
        InputStream in = new FileInputStream(new File(filePathName));
        // 1.加载解析docx文档用的XWPFDocument对象
        XWPFDocument document = new XWPFDocument(in);
        // 2.解析XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
        File imageFolderFile = new File(filePath);
        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
        options.setExtractor(new FileImageExtractor(imageFolderFile));
        options.setIgnoreStylesIfUnused(false);
        options.setFragment(true);
        // 3.将 XWPFDocument转换成XHTML
        OutputStream out = new FileOutputStream(new File(filePath + htmlName));
        XHTMLConverter.getInstance().convert(document, out, options);
    }


    /**
     * @Author: jimmy
     * @Date: 2019/12/10 14:54
     * @Param: []
     * @Return: void
     * @Description: word转换html并生成html文件
     */
    @Test
    void WordToHtmlTest() throws Exception {

        String filePath = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\";
        String singleFileName = "ww";
        //遍历所有文件

        //生成html

        docx(filePath, "temp.docx", singleFileName + ".html");


    }

}
