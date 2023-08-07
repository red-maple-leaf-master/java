package com.example.demo;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class itextPDF {

    public Map<String,String> getData(){
        Map<String,String> map = new HashMap<>();
        //货物编号
        map.put("swgNum","CK202302010001");
        // 仓单类型
        map.put("wrWithdrawStatus","代采");
        //仓单编号
        map.put("wrNum","CD202320220001");
        //存货人
        map.put("chrPeople","张三");
        //购货人
        map.put("goodsOwnTmp","代采资方李四");
        //仓单所有权人
        map.put("syqPeople","客户张三");
        //仓单质押权人
        map.put("wrPledgeeName","金融服务商王五");
        //生成日期
        map.put("wrCreateTime",new Date().toString());
        //仓库地址
        map.put("address","南阳青阳库");
        //存货日期
        map.put("sgWarehousingTime",new Date().toString());
        //仓储物名称
        map.put("goodsName","青阳库");
        //仓储物类型
        map.put("goodsClassificationName","板蓝根");
        //数量
        map.put("wrAvailableStockNum","100");
        //单位
        map.put("goodsUnit","袋");
        //入库时间
        map.put("realWarehousingTime",new Date().toString());
        //包装
        map.put("goodsSpec","一塑料袋");
        //仓单重量
        map.put("wrAvailableStockWeight","1000");
        //保管仓库
        map.put("wwnWarehouseName","青阳库");
        //所在库位
        map.put("wrLocation","10010");
        //货值
        map.put("customerTotalPrice","10000元");
        //货值(大写)
        map.put("bigTotalPrice","一万元");
        //货值(小写)
        map.put("smallTotalPrice","10000元");
        return map;
    }


    @Test
    public void test(){
        String newpdfPath="E:\\Desktop\\";
        // 生成的新文件路径
        String pdfPath = newpdfPath + "电子仓单-仓单所有权人盖章wan001.pdf";

        try {
            //设置中文字体
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // 读取pdf模板
            PdfReader reader = new PdfReader("E:\\Desktop\\代采模板.pdf");
            // 获取字节数组输出流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            // 操作pdf文件
            PdfStamper stamper = new PdfStamper(reader, bos);
            // 获取pdf模板中的属性
            AcroFields form = stamper.getAcroFields();
            // 为属性设置字体
            form.addSubstitutionFont(bf);
            //处理模板文字部分
            Map<String, String> map =getData();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                // 这是设置填充内容格式的代码，此处可以不设置，直接在Adobe Acrobat DC中设置字体、大小等格式
                //form.setFieldProperty(entry.getKey(), "textsize", 8f, null);

                // 根据Adobe Acrobat DC中已经设置好的字段填充对应的值
                form.setField(entry.getKey(), entry.getValue());
            }
            stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑

            stamper.close();
            reader.close();
            //生成pdf路径
            FileOutputStream fos = new FileOutputStream(pdfPath);
            fos.write(bos.toByteArray());
            fos.flush();
            if (fos != null) {
                fos.close();
            }
            if (bos != null) {
                bos.close();
            }
            System.out.println("pdf模板生成了");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
