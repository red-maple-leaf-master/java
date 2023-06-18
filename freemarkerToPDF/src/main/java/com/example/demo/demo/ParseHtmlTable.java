package com.example.demo.demo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;


import org.apache.commons.io.FileUtils;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * html 转换成 pdf
 */
public class ParseHtmlTable {

    public static final String pdfDestPath = "E:\\project\\java\\freemarkerToPDF\\src\\main\\resources\\";
    public static final String htmlPath = "E:\\project\\java\\freemarkerToPDF\\src\\main\\resources\\templates\\oneHTML.html";

    public static void main(String[] args) throws IOException, DocumentException {
        String pdfName = "test.pdf";
        ParseHtmlTable parseHtmlTable = new ParseHtmlTable();
        String htmlStr = FileUtils.readFileToString(new File(htmlPath));
        //  E:\project\java\freemarkerToPDF\src\main\resources\fonts
        String path = "C:\\Windows\\Fonts";
        parseHtmlTable.html2pdf(htmlStr,pdfName ,path);
    }


    public void html2pdf(String html, String pdfName, String fontDir) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ITextRenderer renderer = new ITextRenderer();
            ITextFontResolver fontResolver = (ITextFontResolver) renderer.getSharedContext().getFontResolver();
            //添加字体库 begin
            File f = new File(fontDir);
            if (f.isDirectory()) {
                File[] files = f.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        String lower = name.toLowerCase();
                        return lower.endsWith(".otf") || lower.endsWith(".ttf") || lower.endsWith(".ttc");
                    }
                });
                for (int i = 0; i < files.length; i++) {
                    fontResolver.addFont(files[i].getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                }
            }
            //添加字体库end
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(os);
            renderer.finishPDF();
            byte[] buff = os.toByteArray();
            //保存到磁盘上
            FileUtil.byte2File(buff,pdfDestPath,pdfName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

