package com.example.demo;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class warehouseReceiptPDF {

    private final static String PATH = "E:\\Desktop\\YCT.pdf";


    @Test
    public void createPDF() throws IOException, DocumentException {
        // 1.新建document对象
        Document document = new Document(PageSize.A4, 90, 90, 72, 72);

        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        PdfWriter.getInstance(document, new FileOutputStream(PATH));

        // 3.打开文档
        document.open();

        // Create a PdfFont
//        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/STFANGSO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

//        Font font = new Font(baseFont);
        // 标题 加粗
        Font fangFont = new Font(bf, 14, Font.BOLD);
        Font fangFont01 = new Font(bf, 12, Font.BOLD);
        Font fangFont02 = new Font(bf, 12);

        // 4.添加一个内容段落
        setHeaderStyle("电子仓单", document, fangFont);

        // 创建表格
        PdfPTable table = new PdfPTable(1);
       // 设置表格宽度比例为%100
        table.setWidthPercentage(100);
        // 设置表格的宽度
        table.setTotalWidth(500);
        // 设置表格上面空白宽度
        table.setSpacingBefore(10f);
        // 设置表格下面空白宽度
        table.setSpacingAfter(10f);
        // 构建每个单元格
        PdfPCell cell1 = new PdfPCell(new Paragraph("仓单编号",fangFont02));
        // 边框颜色
/*         cell1.setBorderColor(BaseColor.BLACK);
        // 设置背景颜色
        cell1.setBackgroundColor(BaseColor.WHITE);
       // 设置跨两行
        cell1.setRowspan(2);
        // 设置距左边的距离
        cell1.setPaddingLeft(10);
        // 设置高度
        cell1.setFixedHeight(20);
        // 设置内容水平居中显示
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        // 设置垂直居中
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);*/


        table.addCell(cell1);


        document.add(table);

        // 5.关闭文档
        document.close();
    }


    /**
     * 大标题 设置段前段后  0.5行 行间距 25榜
     *
     * @param text
     */
    public void setHeaderStyle(String text, Document document, Font font) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingAfter(10f);
        paragraph.setSpacingAfter(10f);
//        paragraph.setLeading(25f);// 行间距
        paragraph.setAlignment(Paragraph.ALIGN_LEFT);//设置居中
        document.add(paragraph);
    }
}
