package com.example.demo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
public class itextCreateDemo {

    private final static String PATH = "E:\\project\\java\\freemarkerToPDF\\src\\main\\resources\\YCT.pdf";
    @Test
    public void test() throws IOException, DocumentException {

        Rectangle pageSize = new Rectangle(216f,720f);
        Document doc = new Document(pageSize,30,30,50,50);//设置页面的大小

// 1.新建document对象
        Document document = new Document(pageSize,30,30,50,50);

        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PATH));

        // 3.打开文档
        document.open();

        // Create a PdfFont
        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/simfang.ttf", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);

        Font font = new Font(baseFont);
        // 标题 加粗
        Font fangFont = new Font(bf,14,Font.BOLD);
        Font fangFont01 = new Font(bf,12,Font.BOLD);

        // 4.添加一个内容段落
        Paragraph header = new Paragraph("壹仓通供应链平台融资合同", fangFont);
        header.setAlignment(Paragraph.ALIGN_CENTER);//设置居中
        Paragraph text01 = new Paragraph("甲方（借款方）：", fangFont01);
        Paragraph text02 = new Paragraph("法定代表人：", fangFont01);
        Paragraph text03 = new Paragraph("住所地：", fangFont01);
        Paragraph text04 = new Paragraph("联系方式：", fangFont01);
        Paragraph text05 = new Paragraph("乙方（融资方）：", fangFont01);
        Paragraph text06 = new Paragraph("法定代表人：", fangFont01);
        Paragraph text07 = new Paragraph("住所地：", fangFont01);
        Paragraph text08 = new Paragraph("联系方式：", fangFont01);

        document.add(header);
        document.add(text01);
        document.add(text02);
        document.add(text03);
        document.add(text04);
        document.add(text05);
        document.add(text06);
        document.add(text07);
        document.add(text08);

        // 5.关闭文档
        document.close();
    }
}
