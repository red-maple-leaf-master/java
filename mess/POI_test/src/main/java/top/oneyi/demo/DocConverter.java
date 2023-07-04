//package top.oneyi.demo;
//
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.converter.WordToHtmlConverter;
//import org.w3c.dom.Document;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//public class DocConverter {
//    public static void main(String[] args) throws Exception {
//        // 读取 .doc 文件
//        InputStream inputStream = new FileInputStream("E:\\Desktop\\one\\document.doc");
//        HWPFDocument document = new HWPFDocument(inputStream);
//
//        // 将 .doc 文件转换为 HTML 文档
//        WordToHtmlConverter converter = new WordToHtmlConverter(
//                DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
//        );
//        converter.processDocument(document);
//        Document html = converter.getDocument();
//
//        // 将 HTML 文档写入 .docx 文件
//        OutputStream outputStream = new FileOutputStream("E:\\Desktop\\one\\output.docx");
//        Transformer transformer = TransformerFactory.newInstance().newTransformer();
//        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//        transformer.transform(new DOMSource(html), new StreamResult(outputStream));
//
//        inputStream.close();
//        outputStream.close();
//    }
//}