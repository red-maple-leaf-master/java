package top.oneyi.itextplus.config;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class ParagraphConfig {


    @Resource
    private  Font blueFont;
    @Value("${itext.text}")
    private String text;


    @Bean
    public Paragraph setParagraph(){
        Paragraph paragraph = new Paragraph(text,blueFont);
        try{
            paragraph.setAlignment(1);//设置文字居中 0靠左   1，居中     2，靠右
            paragraph.setIndentationLeft(12);// 左缩进
            paragraph.setIndentationRight(12);// 右缩进
            paragraph.setFirstLineIndent(24);// 首行缩进
            paragraph.setLeading(20f); //行间距
            paragraph.setSpacingBefore(5f); //设置段落上空白
            paragraph.setSpacingAfter(10f); //设置段落下空白
        }catch (Exception e){
            e.printStackTrace();
        }
        return paragraph;
    }
}
