package top.oneyi;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;

import javax.swing.border.Border;
import java.io.*;

public class test {


    @Test
    public void test() throws IOException {
        XWPFDocument docx = new XWPFDocument(
                new FileInputStream("E:\\Desktop\\java_project\\one\\java\\POI_test\\src\\main\\resources\\template\\temp.docx"));
        //using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        System.out.println(we.getText());


    }

    @Test
    public void test01() throws IOException {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream outputStream = new FileOutputStream(new File("E:\\Desktop\\java_project\\one\\java\\POI_test\\src\\main\\resources\\template\\132.docx"));
        // 创建一个段落
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setBorderBottom(Borders.BASIC_BLACK_DASHES);
        paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);
        paragraph.setBorderRight(Borders.BASIC_BLACK_DASHES);
        paragraph.setBorderTop(Borders.BASIC_BLACK_DASHES);

        // 写入段落内容
        XWPFRun run = paragraph.createRun();
        run.setText("    我是一个段落                        的发挥反对恢复空间划分等级的发返回的数据回复房价多少地方符合东方封建" +
                "皇帝尽快恢复阶段开始发返回的数据记得发货的加法和对接客户地方很多技术开发点击返回的是了发的时间发货返回的数据的" +
                "发挥的代价恢复艰苦奋斗和迪斯科解放和大数据开发的回复俩分据记得发货的加法和对接客户地方很多技术开发点击返回的是" +
                "了发的时间发货返回的数据的发挥的代价恢复艰苦奋斗和迪斯科解放和大数据开发的回复俩据记得发货的加法和对接客户地方" +
                "很多技术开发点击返回的是了发的时间发货返回的数据的发挥的代价恢复艰苦奋斗和迪斯科解放和大数据开发的回复俩据记得" +
                "发货的加法和对接客户地方很多技术开发点击返回的是了发的时间发货返回的数据的发挥的代价恢复艰苦奋斗和迪斯科解放和" +
                "大数据开发的回复俩据记得发货的加法和对接客户地方很多技术开发点击返回的是了发的时间发货返回的数据的发挥的代价恢" +
                "复艰苦奋斗和迪斯科解放和大数据开发的回复俩据记得发货的加法和对接客户地方很多技术开发点击返回的是了发的时间发货" +
                "返回的数据的发挥的代价恢复艰苦奋斗和迪斯科解放和大数据开发的回复俩据记得发货的加法和对接客户地方很多技术开发点" +
                "击返回的是了发的时间发货返回的数据的发挥的代价恢复艰苦奋斗和迪斯科解放和大数据开发的回复俩据记得发货的加法和对接" +
                "客户地方很多技术开发点击返回的是了发的时间发货返回的数据的发挥的代价恢复艰苦奋斗和迪斯科解放和大数据开发的回复俩" +
                "据记得发货的加法和对接客户地方很多技术开发点击返回的是了发的时间发货返回的数据的发挥的代价恢复艰苦奋斗和迪斯科解" +
                "放和大数据开发的回复俩据记得发货的加法和对接客户地方很多技术开发点击返回的是了发的时间发货返回的数据的发挥的代价" +
                "恢复艰苦奋斗和迪斯科解放和大数据开发的回复俩");
        document.write(outputStream);
        outputStream.close();
        System.out.println("word生成结束");
    }
}
