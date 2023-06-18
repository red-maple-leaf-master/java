/*
package com.example.demo.demo;
 

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;

import org.apache.commons.lang3.StringUtils;

 
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SealCreatePDFUtil2 {
    
    private static Logger logger = Logger.getLogger("bane");
 
    // 利用模板生成pdf
    public static void createPdf(InputStream templateInputStream,OutputStream newPdfOutputStream,InputStream imgInputStream,Map<String, String> parametersMap,float img_X_position,float img_Y_position) {
        PdfReader pdfReader;
        ByteArrayOutputStream byteArrayOutputStream;
        PdfStamper pdfStamper;
        try {
            pdfReader = new PdfReader(templateInputStream);// 读取pdf模板
            byteArrayOutputStream = new ByteArrayOutputStream();
            pdfStamper = new PdfStamper(pdfReader, byteArrayOutputStream);
            AcroFields acroFields = pdfStamper.getAcroFields();
 
            // 根据填充内容长度设置字体大小
            if(MapUtils.isNotEmpty(parametersMap)){
                BaseFont baseFont = BaseFont.createFont( "STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
 
                //  循环参数map
                for(String key:parametersMap.keySet()){
                    acroFields.setField(key,parametersMap.get(key));// 将map中的参数设置到pdf中。 
                    acroFields.setFieldProperty(key,"textfont",baseFont,null); // 为每一个表单域 设置字体
                }
                
                for(String key:parametersMap.keySet()){
                    if(StringUtils.isNoneBlank(key,parametersMap.get(key))){
                        int ilen = parametersMap.get(key).toString().length();
                        logger.debug("key: " + key+", 长度： " + ilen);
                        if(StringUtils.equalsIgnoreCase("syzn_hjdz",key) // 生育子女户籍地址
                        || StringUtils.equalsIgnoreCase("syzn_xjzdz",key) // 生育子女现居住地址
                        ){
                            setFontSizeByLength_1(ilen,key,acroFields);
                        }else if(StringUtils.equalsIgnoreCase("bz",key) 
                                || StringUtils.equalsIgnoreCase("hjdz",key)// 户籍地址
                                || StringUtils.equalsIgnoreCase("jtzz",key)// 家庭住址
                        ){
                            setFontSizeByLength_3(ilen,key,acroFields);
                        }else{
                            setFontSizeByLength_2(ilen,key,acroFields);
                        }
                    }
                }
            }
            
 
            */
/**
             * 如果实参传入了，图片和坐标值，则添加图片
             *//*

            byte[] bytes = toByteArray(imgInputStream);
            if(bytes != null && img_X_position > 0 && img_Y_position > 0){
                Image image = Image.getInstance(bytes);
                image.setAbsolutePosition(img_X_position,img_Y_position);
                PdfContentByte underContent = pdfStamper.getOverContent(1);//  getOverContent 图片会覆盖在文字上层
                image.scaleAbsolute(80,120);// 指定图片的宽高
                underContent.addImage(image);
            }
            
            pdfStamper.setFormFlattening(true);// 如果为false那么生成的PDF文件还能编辑，一定要设为true
            pdfStamper.close();
            Document document = new Document();
            PdfCopy pdfCopy = new PdfCopy(document, newPdfOutputStream);
            document.open();
            PdfImportedPage importPage = pdfCopy.getImportedPage(new PdfReader(byteArrayOutputStream.toByteArray()), 1);
            pdfCopy.addPage(importPage);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
 
    
    
    
 
 
	
	public static Map<String,String> initPdfParametersMap(){
        Map<String,String> parametersMap=new HashMap<String, String>();
        parametersMap.put("sqrxm", "李四");// 申请人姓名
        parametersMap.put("xb", "男");
        return parametersMap;
    }
 
 
 
 
 
    */
/**
     * InputStream 转 byte[]
     * @param input
     * @return
     *//*

    public static byte[] toByteArray(InputStream input){
        if(input == null){
            return null;
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (true) {
            try {
                if (!(-1 != (n = input.read(buffer)))) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
 
 
 
 
    */
/**
     * 根据内容长度设置字体大小，
     * 本方法适合 《短》 的文本域
     * @param iLength
     * @param key
     * @param acroFields
     *//*

    public static void setFontSizeByLength_1(int iLength,String key,AcroFields acroFields){
        if(iLength > 0 && iLength < 5){
            acroFields.setFieldProperty(key, "textsize", new Float(12), null);
        } else if(iLength >= 5 && iLength < 10){
            acroFields.setFieldProperty(key, "textsize", new Float(10), null);
        }else if(iLength >= 15 && iLength <= 20){
            acroFields.setFieldProperty(key, "textsize", new Float(8), null);
        }else if(iLength > 20){
            acroFields.setFieldProperty(key, "textsize", new Float(7), null);
        }
    }
 
 
    */
/**
     * 根据内容长度设置字体大小，
     * 本方法适合 《中》 的文本域
     * @param iLength
     * @param key
     * @param acroFields
     *//*

    public static void setFontSizeByLength_2(int iLength,String key,AcroFields acroFields){
        if(iLength > 0 && iLength < 15){
            acroFields.setFieldProperty(key, "textsize", new Float(12), null);
        } else if(iLength >= 15 && iLength < 30){
            acroFields.setFieldProperty(key, "textsize", new Float(10), null);
        }else if(iLength >= 35 && iLength <= 50){
            acroFields.setFieldProperty(key, "textsize", new Float(7), null);
        }else if(iLength > 50){
            acroFields.setFieldProperty(key, "textsize", new Float(6), null);
        }
    }
 
 
    */
/**
     * 根据内容长度设置字体大小，
     * 本方法适合 《长》 的文本域
     * @param iLength
     * @param key
     * @param acroFields
     *//*

    public static void setFontSizeByLength_3(int iLength,String key,AcroFields acroFields){
        if(iLength > 0 && iLength < 20){
            acroFields.setFieldProperty(key, "textsize", new Float(12), null);
        } else if(iLength >= 20 && iLength < 50){
            acroFields.setFieldProperty(key, "textsize", new Float(10), null);
        }else if(iLength >= 50 && iLength <= 100){
            acroFields.setFieldProperty(key, "textsize", new Float(7), null);
        }else if(iLength > 100){
            acroFields.setFieldProperty(key, "textsize", new Float(6), null);
        }
    }
 
 
 
 
 
    
    
 
 
}*/
