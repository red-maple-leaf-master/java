//package top.oneyi.utils;
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.converter.WordToHtmlConverter;
//import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
//import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
//
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.w3c.dom.Document;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.*;
//
///**
// * 创建人:jimmy
// * 创建时间:2019年12月05日20:21
// * 描述:word转换html返回html内容 工具类
// */
//public class WordToHtmlBackStringUtil {
//
//    /**
//     * @Author: jimmy
//     * @Date: 2019/12/10 10:23
//     * @Param: [filePath, fileName, htmlName]
//     * @Return: java.lang.String
//     * @Description: word转html方法
//     */
//    public static String convert(String filePath) throws Exception {
//        //获取文件名称
//        File file = new File(filePath);
//        String fileName = file.getName();
//
//        //获取文件所在目录
//        if (filePath.lastIndexOf("/") > 0){
//            filePath = filePath.substring(0,filePath.lastIndexOf("/") + 1);
//        }else if (filePath.lastIndexOf("\\") > 0){
//            filePath = filePath.substring(0,filePath.lastIndexOf("\\") + 1);
//        }
//
//        //获取文件html名称
//        String htmlName = fileName.substring(0, fileName.lastIndexOf(".")) + ".html";
//        if (fileName.endsWith(".docx") || fileName.endsWith(".DOCX")) {
//            docx(filePath, fileName, htmlName);
//        } else {
//            doc(filePath, fileName, htmlName);
//        }
//        String readfile = readfile(filePath + htmlName);
//        return readfile;
//    }
//
//    /**
//     * @Author: jimmy
//     * @Date: 2019/12/10 12:34
//     * @Param: [filePath, fileName, htmlName]
//     * @Return: void
//     * @Description: 转换docx
//     */
//    public static void docx(String filePath ,String fileName,String htmlName) throws Exception{
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
//    /**
//     * @Author: jimmy
//     * @Date: 2019/12/10 12:34
//     * @Param: [filePath, fileName, htmlName]
//     * @Return: void
//     * @Description: 转换doc
//     */
//    public static void doc(String filePath ,String fileName,String htmlName) throws Exception{
//        final String filePathName = filePath + fileName;
//        InputStream input = new FileInputStream(new File(filePathName));
//        //1.加载解析doc文档用的HWPFDocument对象
//        HWPFDocument wordDocument = new HWPFDocument(input);
//        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
//        //解析word文档
//        wordToHtmlConverter.processDocument(wordDocument);
//        Document htmlDocument = wordToHtmlConverter.getDocument();
//
//        File htmlFile = new File(filePath + htmlName);
//        OutputStream outStream = new FileOutputStream(htmlFile);
//
//        DOMSource domSource = new DOMSource(htmlDocument);
//        StreamResult streamResult = new StreamResult(outStream);
//
//        TransformerFactory factory = TransformerFactory.newInstance();
//        Transformer serializer = factory.newTransformer();
//        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
//        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
//        serializer.setOutputProperty(OutputKeys.METHOD, "html");
//
//        serializer.transform(domSource, streamResult);
//        outStream.close();
//    }
//
//    /**
//     * @Author: jimmy
//     * @Date: 2019/12/10 10:20
//     * @Param: [filePath]
//     * @Return: java.lang.String
//     * @Description: 读取poi生成的html文件内容返回
//     */
//    public static String readfile(String filePath) {
//        File file = new File(filePath);
//        InputStream input = null;
//        try {
//            input = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        StringBuffer buffer = new StringBuffer();
//        byte[] bytes = new byte[1024];
//        try {
//            for (int i; (i = input.read(bytes)) != -1;) {
//                buffer.append(new String(bytes, 0, i, "utf8"));
//            }
//            //流记得关闭，不然后面file不能被正常删除
//            input.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //转换成功后的html文件内容读取后删掉，防止不必要的占用服务器资源
//        file.delete();
//        return buffer.toString();
//    }
//
//}
//
