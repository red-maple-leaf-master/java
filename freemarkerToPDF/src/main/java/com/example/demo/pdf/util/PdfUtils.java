package com.example.demo.pdf.util;


import com.example.demo.pojo.EnterpriseBaseInfo;
import com.example.demo.pojo.EnterpriseInfo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class PdfUtils {
    /**
     * 设置
     *
     * @param fields
     * @param data
     * @throws IOException
     * @throws DocumentException
     */
    private static void fillData(AcroFields fields, Map<String, String> data) throws IOException, DocumentException {
        List<String> keys = new ArrayList<String>();
        BaseFont baseFont = BaseFont.createFont("E:\\project\\java\\freemarkerToPDF\\src\\main\\resources\\fonts\\STFANGSO.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font();
        font.setStyle(1);


        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//        Font font = new Font(bf, 12, Font.BOLD);
//Font font = FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD, BaseColor.RED);


        Map<String, AcroFields.Item> formFields = fields.getFields();
        for (String key : data.keySet()) {
            if (formFields.containsKey(key)) {
                String value = data.get(key);
                fields.setFieldProperty(key, "textfont", baseFont, null); // 为每一个表单域 设置字体
                fields.setField(key, value); // 为字段赋值,注意字段名称是区分大小写的
                keys.add(key);
            }
        }
        Iterator<String> itemsKey = formFields.keySet().iterator();
        while (itemsKey.hasNext()) {
            String itemKey = itemsKey.next();
            if (!keys.contains(itemKey)) {
                fields.setField(itemKey, " ");
            } else {

            }
        }
    }

    /**
     * @param templatePdfName 模板pdf名称
     * @param generatePdfPath 生成pdf路径
     * @param data            数据
     */
    public static String generatePDF(String templatePdfName, String generatePdfPath, Map<String, String> data) {
        OutputStream fos = null;
        ByteArrayOutputStream bos = null;
        try {
            byte[] bytes = generatePDF(templatePdfName, data);
            fos = new FileOutputStream(generatePdfPath);
            fos.write(bytes);
            fos.flush();
            return generatePdfPath;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * @param templatePdfName 模板pdf名称
     * @param data            数据
     */
    public static byte[] generatePDF(String templatePdfName, Map<String, String> data) {
        ByteArrayOutputStream bos = null;
        try {
//            InputStream istemplate =PdfUtils.class.getClassLoader().getResourceAsStream(templatePdfName);
            InputStream istemplate = new FileInputStream(templatePdfName);
            PdfReader reader = new PdfReader(istemplate);
            bos = new ByteArrayOutputStream();
            /* 将要生成的目标PDF文件名称 */
            PdfStamper ps = new PdfStamper(reader, bos);
//            InputStream templateInputStream = new ClassPathResource("fonts/simfang.ttf").getInputStream();
//            byte[] fontByte = new byte[templateInputStream.available()];
//            templateInputStream.read(fontByte);
//            BaseFont font = BaseFont.createFont("simfang.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED,BaseFont.NOT_CACHED,fontByte,fontByte);

            ArrayList<BaseFont> fontList = new ArrayList<>();
//            /* 使用中文字体 */
//            BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/simfang.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            fontList.add(bf);
//            fontList.add(font);
            BaseFont baseFont = BaseFont.createFont("E:\\project\\java\\freemarkerToPDF\\src\\main\\resources\\fonts\\STFANGSO.TTF,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            Font font = new Font();
            font.setStyle(1);

            /* 取出报表模板中的所有字段 */
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            fillData(fields, data);
            /* 必须要调用这个，否则文档不会生成的  如果为false那么生成的PDF文件还能编辑，一定要设为true*/
            ps.setFormFlattening(true);
            ps.close();
            return bos.toByteArray();
            // return bos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void exportPDF(HttpServletResponse response, String fileName, byte[] pdfBytes) throws IOException {
        if (StringUtils.isBlank(fileName)) {
            //当前日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            fileName = sdf.format(new Date());
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".pdf");
        ServletOutputStream out = response.getOutputStream();
        // 使用工具类直接将文件的字节复制到响应输出流中

        //FileCopyUtils.copy(new FileInputStream(new File("123456.pdf")), out);
        //out.write(pdfBytes);
        //out.write(pdfBytes);
        //通过输入流获取图片数据
//        InputStream inStream = conn.getInputStream();
//        byte[] buffer = new byte[1024];
//        int len;
//        baos = new ByteArrayOutputStream();
//        while ((len=inStream.read(buffer))!=-1){
//            baos.write(buffer,0,len);
//        }
//        response.addHeader("Content-Disposition", "attachment;filename=" + url);
//        response.addHeader("Content-Length", "" + baos.size());
//        response.setHeader("filename", url);
//        response.setContentType("application/octet-stream");
//        out = response.getOutputStream();
        out.write(pdfBytes);

        out.flush();
    }

    public static void main(String[] args) {


        String source = "C:\\Users\\13621\\Desktop\\contractTemplate.pdf";
        String target = "C:\\Users\\13621\\Desktop\\ttt.pdf";


        generatePDF(source, target, getWordToPDFData());
    }


    /**
     * 获取生成Word文档所需要的数据
     */
    private static Map<String, String> getWordToPDFData() {

        Map<String, String> dataMap = new HashMap<>();

        EnterpriseBaseInfo enterpriseBaseInfo = new EnterpriseBaseInfo();
        enterpriseBaseInfo.setEnterpriseName("测试甲方企业");
        enterpriseBaseInfo.setLegalPersonName("张三");
        enterpriseBaseInfo.setLegalPersonAddress("上海明哲路");
        enterpriseBaseInfo.setLegalPersonPhone("15562023564");
        dataMap.put("enterpriseBaseInfo.enterpriseName", enterpriseBaseInfo.getEnterpriseName());
        dataMap.put("enterpriseBaseInfo.legalPersonName", enterpriseBaseInfo.getLegalPersonName());
        dataMap.put("enterpriseBaseInfo.legalPersonAddress", enterpriseBaseInfo.getLegalPersonAddress());
        dataMap.put("enterpriseBaseInfo.legalPersonPhone", enterpriseBaseInfo.getLegalPersonPhone());

        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setEnterpriseName("测试乙方企业");
        enterpriseInfo.setEnterpriseLegalPerson("李四");
        enterpriseInfo.setDomicile("广东山策路");
        enterpriseInfo.setContact("15562356532");
        enterpriseInfo.setAccountName("第五轻柔");
        enterpriseInfo.setBankAccount("132146546546");
        enterpriseInfo.setDepositBank("测试乙方开户行");

        dataMap.put("enterpriseInfo.enterpriseName", enterpriseInfo.getEnterpriseName());
        dataMap.put("enterpriseInfo.enterpriseLegalPerson", enterpriseInfo.getEnterpriseLegalPerson());
        dataMap.put("enterpriseInfo.domicile", enterpriseInfo.getDomicile());
        dataMap.put("enterpriseInfo.contact", enterpriseInfo.getContact());
        dataMap.put("enterpriseInfo.accountName", enterpriseInfo.getAccountName());
        dataMap.put("enterpriseInfo.bankAccount", enterpriseInfo.getBankAccount());
        dataMap.put("enterpriseInfo.depositBank", enterpriseInfo.getDepositBank());


        dataMap.put("loanAmount", "12342");
        dataMap.put("capitalLoanAmount", "一二三四");
        dataMap.put("timeLimit", "12");
        dataMap.put("termType", "月");
        dataMap.put("procedureInterestRate", "0.02");
        dataMap.put("partyBankAccount", "账号");
        return dataMap;
    }
}