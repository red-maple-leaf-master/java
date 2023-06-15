//package top.oneyi.utils;
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.converter.WordToHtmlConverter;
//import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
//import fr.opensagres.poi.xwpf.converter.core.FileURIResolver;
//import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
//import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
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
// * 描述:word转换html工具类
// */
//public class WordToHtmlUtil {
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
//}
