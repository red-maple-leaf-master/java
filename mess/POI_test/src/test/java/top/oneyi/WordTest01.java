//package top.oneyi;
//
//
//import com.documents4j.api.DocumentType;
//import com.documents4j.api.IConverter;
//import com.documents4j.job.LocalConverter;
//import org.apache.poi.xwpf.converter.core.BasicURIResolver;
//import org.apache.poi.xwpf.converter.core.FileImageExtractor;
//import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
//import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.ResourceUtils;
//
//import java.io.*;
//
//
//
//@SpringBootTest
//public class WordTest01 {
//    private final static String PATH = "/ssd/gyl/temp";
//    @Test
//    public void test() throws IOException {
//
//        String sourceFileName = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\temp.docx";
//        //转换成的html文件(不存在将会被创建，存在会被覆盖)
//        String targetFileName = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\123.html";
//
//        File file = new File(PATH + "/123.html");
//        OutputStreamWriter outputStreamWriter = null;
//        try {
//            XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
//            XHTMLOptions options = XHTMLOptions.create();
//
//            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
//            XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
//            xhtmlConverter.convert(document, outputStreamWriter, options);
//        } finally {
//            if (outputStreamWriter != null) {
//                outputStreamWriter.close();
//            }
//        }
//    }
//
//    @Test
//    public void test01(){
//        File inputWord = new File("E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\temp.docx");
//        File outputFile = new File("E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\temp01.pdf");
//        try  {
//            InputStream docxInputStream = new FileInputStream(inputWord);
//
//            OutputStream outputStream = new FileOutputStream(outputFile);
//            IConverter converter = LocalConverter.builder().build();
//            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
//
//            outputStream.close();
//            System.out.println("success");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
