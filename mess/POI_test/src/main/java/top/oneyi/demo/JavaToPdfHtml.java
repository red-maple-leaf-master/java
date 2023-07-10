package top.oneyi.demo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by lujianing on 2017/5/7.
 */
public class JavaToPdfHtml {

    private static final String DEST = "E:\\Desktop\\java_project\\one\\java\\POI_test\\src\\main\\resources\\one\\HelloWorld_CN_HTML.pdf";
    private static final String HTML = "E:\\Desktop\\java_project\\one\\java\\POI_test\\src\\main\\resources\\one\\redoneHTML.ftl";
    private static final String FONT = "E:\\Desktop\\java_project\\one\\java\\POI_test\\src\\main\\resources\\one\\simhei.ttf";


    // 根据html生成
    public static void main(String[] args) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
//        fontImp.register(FONT);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream("E:\\Desktop\\java_project\\one\\java\\POI_test\\src\\main\\resources\\one\\11.html"), null, StandardCharsets.UTF_8, fontImp);
        // step 5
        document.close();


    }
}