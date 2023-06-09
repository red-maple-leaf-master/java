//package top.oneyi;
//
//
//// https://blog.csdn.net/yuxielea/article/details/103477874
//
//import fr.opensagres.poi.xwpf.converter.core.BasicURIResolver;
//import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
//import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
//import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
//import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.junit.jupiter.api.Test;
//
//import top.oneyi.utils.WordToHtmlBackStringUtil;
//import top.oneyi.utils.WordToHtmlUtil;
//
//import java.io.*;
//
//public class WordTest {
//
//    /**
//     * @Author: jimmy
//     * @Date: 2019/12/10 12:34
//     * @Param: [filePath, fileName, htmlName]
//     * @Return: void
//     * @Description: 转换docx
//     */
//    public static void docx(String filePath, String fileName, String htmlName) throws Exception {
//        final String filePathName = filePath + fileName;
//        InputStream in = new FileInputStream(new File(filePathName));
//        // 1.加载解析docx文档用的XWPFDocument对象
//        XWPFDocument document = new XWPFDocument(in);
//        // 2.解析XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
//        File imageFolderFile = new File(filePath);
//        XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
//        options.setExtractor(new FileImageExtractor(imageFolderFile));
//        options.setIgnoreStylesIfUnused(false);
//        options.setFragment(true);
//        // 3.将 XWPFDocument转换成XHTML
//        OutputStream out = new FileOutputStream(new File(filePath + htmlName));
//        XHTMLConverter.getInstance().convert(document, out, options);
//    }
//
//    @Test
//    void WordToHtmlTest() throws Exception {
//
//        String filePath = "E:\\Desktop\\java_project\\one\\java\\POI_test\\src\\main\\resources\\template\\";
//
//        String singleFileName = "temp.html";
//
//        //生成html
//        WordToHtmlUtil.docx(filePath, "temp.docx", singleFileName);
////        docx(filePath, "temp.docx", singleFileName);
//
////        String readfile = WordToHtmlBackStringUtil.readfile(filePath + singleFileName);
////        System.out.println("readfile = " + readfile );
//
//    }
//    @Test
//    public void test01(){
//        String file = "E:\\Desktop\\java_project\\one\\java\\API_doc\\src\\main\\resources\\template\\temp.html";
//        // 获取HTML文件流
//        StringBuffer htmlSb = new StringBuffer();
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(
//                    new FileInputStream(file), "utf-8"));
//            while (br.ready()) {
//                htmlSb.append(br.readLine());
//            }
//            br.close();
//            // 删除临时文件
//            //file.delete();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // HTML文件字符串
//        String htmlStr = htmlSb.toString();
//        // 返回经过清洁的html文本
//        System.out.println( htmlStr);
//    }
//}
