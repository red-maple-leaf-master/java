package com.example.demo;


import com.example.demo.pdf.core.PdfReplacer;
import com.example.demo.pdf.core.PdfReplacerBuilder;

import com.example.demo.pdf.core.PositionRenderListener;
import com.example.demo.pdf.rule.BankCardReplace;
import com.example.demo.pdf.rule.ReplaceRule;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class itextCreatePDF {


    private final static String PATH = "C:\\Users\\13621\\Desktop\\test.pdf";

    @Test
    public void test01() throws DocumentException, IOException {
        // 1.新建document对象
        Document document = new Document();

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
        Font font01 = new Font(bf);

        // 4.添加一个内容段落
        document.add(new Paragraph("Hello World!",font));
        document.add(new Paragraph("我是中文",font));
        document.add(new Paragraph("我是中文",font01));
        document.add(new Paragraph("我是中文",font01));


        // 5.关闭文档
        document.close();
    }

    /**
     * 写入简单的段落
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PATH));
        //打开文件
        document.open();

        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        //蓝色字体  段落样式
        Font blueFont = new Font(bfChinese);
        blueFont.setColor(BaseColor.BLUE);
        //段落文本
        Paragraph paragraphBlue = new Paragraph("你好啊   是的封建士大夫", blueFont);
        document.add(paragraphBlue);

        //绿色字体
        Font greenFont = new Font(bfChinese);
        greenFont.setColor(BaseColor.GREEN);
        //创建章节
        Paragraph chapterTitle = new Paragraph("段落标题xxxx", greenFont);
        Chapter chapter1 = new Chapter(chapterTitle, 1);
        chapter1.setNumberDepth(0);

        Paragraph sectionTitle = new Paragraph("部分标题", greenFont);
        Section section1 = chapter1.addSection(sectionTitle);

        Paragraph sectionContent = new Paragraph("部分内容", blueFont);
        section1.add(sectionContent);

        //将章节添加到文章中
        document.add(chapter1);

        //关闭文档
        document.close();
        //关闭书写器
        writer.close();
    }

    /**
     * 读取已有的PDF
     */
    @Test
    public void test03() throws Exception {
        //读取pdf文件
        PdfReader pdfReader = new PdfReader("C:\\Users\\13621\\Desktop\\test.pdf");

        //修改器
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream("C:\\Users\\13621\\Desktop\\test01.pdf"));

        Image image = Image.getInstance("C:\\Users\\13621\\Desktop\\snipaste_20230617_121746.png");
        image.scaleAbsolute(50, 50);
        image.setAbsolutePosition(0, 700);


        for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
            PdfContentByte content = pdfStamper.getUnderContent(i);
            content.addImage(image);
        }

        pdfStamper.close();

    }


    static Map<String, ReplaceRule> bankRuleMap = new HashMap<>();

    static {
        // 第一步：配置收付款账号的脱敏规则上下文 bankRuleMap
        ReplaceRule bankCardReplace = new BankCardReplace();
        // 招商银行
        bankRuleMap.put("付款账号： ", bankCardReplace);
        bankRuleMap.put("收款人账号：", bankCardReplace);
    }


    @Test
    public void test04() throws Exception {
        String source = "C:\\Users\\13621\\Desktop\\testpdf.pdf";
        String target = "C:\\Users\\13621\\Desktop\\target.pdf";


        // 第一步配置监听器，构建PdfReplacer
        PositionRenderListener positionRenderListener = new PositionRenderListener(bankRuleMap.keySet());

        PdfReplacer pdfReplacer = new PdfReplacerBuilder()
                .setReplaceRuleMap(bankRuleMap)
                .setSourcePdf(source)
                .setTargetPdf(target)
                .setResultRenderListener(positionRenderListener)
                .build();
        try {
            // 第二步执行
            pdfReplacer.process();
        } finally {
            // 第三步回收资源
            pdfReplacer.close();
        }

    }
}
