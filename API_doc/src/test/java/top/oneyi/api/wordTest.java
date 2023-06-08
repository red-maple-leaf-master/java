package top.oneyi.api;




import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.apache.tomcat.util.http.ResponseUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.oneyi.api.common.utils.PdfUtil;
import top.oneyi.api.entity.Data;

import java.io.*;
import java.net.URL;
import java.util.Objects;


@SpringBootTest//加载 SpringBoot 启动类
public class wordTest {


    @Test
    public void test01(){
        String wordPath = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\output.docx";
        String pdfPath = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\www.pdf";

        PdfUtil.docxToPdf(wordPath,pdfPath);

        File inputFile = new File(wordPath);
        File outputFile = new File(pdfPath);
        try {
            InputStream inputStream = new FileInputStream(inputFile);
            OutputStream outputStream = new FileOutputStream(outputFile);


            IConverter converter = LocalConverter.builder().build();
            converter.convert(inputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();




            ByteArrayInputStream parse = parse(outputStream);
            OutputStream outputStream01 = new FileOutputStream("E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\www001.pdf");
            converter.convert(parse).as(DocumentType.DOCX).to(outputStream01).as(DocumentType.PDF).execute();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        Convert2Pdf.doc2Pdf(wordPath,pdfPath);
        System.out.println("转换成功");
        File file = new File(pdfPath);
        ResponseUtil.onlineReader(file,response);*/
    }
    // outputStream转inputStream
    public static ByteArrayInputStream parse(OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    @Test
    public void test02(){
        //1.调用类对象的getResource方法获取文件
        URL resource = Test.class.getResource("");
        System.out.println(resource);

        URL ifFileNotExist = Test.class.getResource("test.txt");
        System.out.println(resource);

        //2.调用类加载器的getResource方法获取文件
        resource = Test.class.getClassLoader().getResource("test.txt");
        System.out.println(resource);

        //3.调用类加载器的getResource方法获取文件，并调用返回的url对象的getPath获取路径
        String path = Objects.requireNonNull(Test.class.getClassLoader().getResource("test.txt")).getPath();
        System.out.println(path);

        //4.调用系统变量的getProperty方法获取user.dir路径
        String property = System.getProperty("user.dir");
        System.out.println(property);

    }

}
