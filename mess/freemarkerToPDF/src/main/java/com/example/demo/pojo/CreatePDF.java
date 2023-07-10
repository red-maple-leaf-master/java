package com.example.demo.pojo;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
    /**
     * 展示的文本
     */
    private List<String> text;
    /**
     * 字体样式
     */
    private Map<String, Font> font;
    /**
     * pdf主体
     */
    private Document document;
    /**
     * 段后
     */
    private float spacingAfter;
    /**
     * 段前
     */
    private float spacingBefore;
    /**
     * 行间距
     */
    private float leading;
    /**
     * 首行缩进
     */
    private float firstLineIndent;
    /**
     * 添加下划线参数 1
     */
    private float thickness;
    /**
     * 添加下划线参数 2
     */
    private float yPosition;

    public CreatePDF() {
        // 1.新建document对象
        this.document = new Document(PageSize.A4, 90, 90, 72, 72);

    }


    /**
     * 设置段前段后  0.5行  首行缩进2字符 行间距 25榜
     *
     * @param text
     */
    public void setStyle(Paragraph text) {
        text.setSpacingAfter(10f);
        text.setSpacingBefore(10f);
        text.setFirstLineIndent(20f);//设置首行缩进
        text.setLeading(25f);// 行间距
    }

    /**
     * 大标题 设置段前段后  0.5行 行间距 25榜
     *
     * @param text
     */
    public void setHeaderStyle(String text, Document document, Font font) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingAfter(10f);
        paragraph.setSpacingBefore(10f);
        paragraph.setLeading(25f);// 行间距
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);//设置居中
        document.add(paragraph);
    }

    /**
     * 开头设置加粗 不缩进
     *
     * @param text
     * @param document
     * @param font
     * @throws DocumentException
     */
    public void setFontStyleAndStyle(String text, Document document, Font font) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setSpacingAfter(10f);
        paragraph.setSpacingBefore(10f);
        document.add(paragraph);
    }

    /**
     * 正文 没有下划线样式
     *
     * @param text
     * @param document
     * @param font
     * @throws DocumentException
     */
    public void setFontStyle(String text, Document document, Font font) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, font);
        setStyle(paragraph);
        document.add(paragraph);
    }

    /**
     * 正文 有下划线样式
     *
     * @param document
     * @throws DocumentException
     */
    public void setFontStyle(Document document, Phrase phrase) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        setStyle(paragraph);
        document.add(paragraph);
    }

    /**
     * 设置下划线
     */
    public void setUnderLine(Phrase phrase, String text, boolean flag, Font font) {

        if (flag) {
            Chunk chunk = new Chunk("  " + text + "  ", font);
            chunk.setUnderline(0.2f, -1);
            phrase.add(chunk);
        } else {
            Chunk chunk = new Chunk(text, font);
            phrase.add(chunk);
        }

    }

}
