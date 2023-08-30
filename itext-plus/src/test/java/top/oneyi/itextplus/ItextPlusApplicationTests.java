package top.oneyi.itextplus;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@SpringBootTest
class ItextPlusApplicationTests {

    private final static String LOCAL_PATH = "E:\\Desktop\\YCT.pdf";

    /**
     * 创建一个简单pdf
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    @Test
    void contextLoads() throws FileNotFoundException, DocumentException {
        //创建文件
        Document document = new Document();
        //建立一个书写器
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(LOCAL_PATH));
        document.open();
        // 创建段落
        Paragraph paragraph = new Paragraph("Hello itext");
        document.add(paragraph);
        // 关闭 document
        document.close();
        //关闭书写器
        writer.close();
    }
    @Test
    void test01() throws IOException, DocumentException {


        Document doc = new Document(PageSize.A4, 90, 90, 72, 72);

        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(LOCAL_PATH));

        //PDF版本(默认1.4)
        writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);

        //文档属性
        doc.addTitle("Title@sample");
        doc.addAuthor("Author@rensanning");
        doc.addSubject("Subject@iText sample");
        doc.addKeywords("Keywords@iText");
        doc.addCreator("Creator@iText");

        //页边空白
        doc.setMargins(90, 90, 72, 72);

        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font blueFont = new Font(bfChinese);
        doc.open();
        setParagraph(doc, blueFont);
        setParagraph(doc, blueFont);
        setParagraph(doc, blueFont);

        // 关闭 document
        doc.close();
        //关闭书写器
        writer.close();
    }

    private void setParagraph(Document doc, Font blueFont) throws DocumentException {
        Paragraph paragraph = new Paragraph("Hello World",blueFont);

        paragraph.setAlignment(1);//设置文字居中 0靠左   1，居中     2，靠右
        paragraph.setIndentationLeft(12);// 左缩进
        paragraph.setIndentationRight(12);// 右缩进
        paragraph.setFirstLineIndent(24);// 首行缩进
        paragraph.setLeading(20f); //行间距
        paragraph.setSpacingBefore(5f); //设置段落上空白
        paragraph.setSpacingAfter(10f); //设置段落下空白
        doc.add(paragraph);
    }

    private void setOneParagraph(Document doc, Font blueFont) throws DocumentException {
        Paragraph paragraph = new Paragraph("Hello World",blueFont);

        paragraph.setAlignment(1);//设置文字居中 0靠左   1，居中     2，靠右
        paragraph.setIndentationLeft(12);// 左缩进
        paragraph.setIndentationRight(12);// 右缩进
        paragraph.setFirstLineIndent(24);// 首行缩进
        paragraph.setLeading(20f); //行间距
        paragraph.setSpacingBefore(5f); //设置段落上空白
        paragraph.setSpacingAfter(10f); //设置段落下空白
        doc.add(paragraph);
    }

    private void setTwoParagraph(Document doc, Font blueFont) throws DocumentException {
        Paragraph paragraph = new Paragraph("Hello World",blueFont);

        paragraph.setAlignment(1);//设置文字居中 0靠左   1，居中     2，靠右
        paragraph.setIndentationLeft(12);// 左缩进
        paragraph.setIndentationRight(12);// 右缩进
        paragraph.setFirstLineIndent(24);// 首行缩进
        paragraph.setLeading(20f); //行间距
        paragraph.setSpacingBefore(5f); //设置段落上空白
        paragraph.setSpacingAfter(10f); //设置段落下空白
        doc.add(paragraph);
    }

    private void setStreeParagraph(Document doc, Font blueFont) throws DocumentException {
        Paragraph paragraph = new Paragraph("Hello World",blueFont);

        paragraph.setAlignment(1);//设置文字居中 0靠左   1，居中     2，靠右
        paragraph.setIndentationLeft(12);// 左缩进
        paragraph.setIndentationRight(12);// 右缩进
        paragraph.setFirstLineIndent(24);// 首行缩进
        paragraph.setLeading(20f); //行间距
        paragraph.setSpacingBefore(5f); //设置段落上空白
        paragraph.setSpacingAfter(10f); //设置段落下空白
        doc.add(paragraph);
    }
}
