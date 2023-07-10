package com.example.demo;

import com.example.demo.pojo.ContractParticularsVo;
import com.example.demo.pojo.EnterpriseBaseInfo;
import com.example.demo.pojo.EnterpriseInfo;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
    private static final String TEMPLATE = "E:\\project\\java\\freemarkerToPDF\\src\\main\\resources\\templates\\";//模板存储路径
    private static final String CONTRACT = "E:\\project\\java\\freemarkerToPDF\\src\\main\\resources\\";


    private static final String PDFNAME = "pdfDemo";//pdf文件名
    private static final String HTMLNAME = "oneHTML";//html文件名


    @Resource
    private Configuration configuration;

    @Test
    void contextLoads() throws Exception {
        contractHandler(HTMLNAME, getWordData());
    }


    public void contractHandler(String templateName, Map<String, Object> paramMap) throws Exception {
        // 获取本地模板存储路径、合同文件存储路径
        String templatePath = TEMPLATE;
        String contractPath = CONTRACT;
        // 组装html和pdf合同的全路径URL
        String localHtmlUrl = contractPath + HTMLNAME + ".html";
        String localPdfPath = contractPath + "/";
        // 判断本地路径是否存在如果不存在则创建
        File localFile = new File(localPdfPath);
        if (!localFile.exists()) {
            localFile.mkdirs();
        }
        String localPdfUrl = localFile + "/" + PDFNAME + ".pdf";
        templateName = templateName + ".ftl";
        htmHandler(templatePath, templateName, localHtmlUrl, paramMap);// 生成html合同
        pdfHandler(localHtmlUrl, localPdfUrl);// 根据html合同生成pdf合同
        deleteFile(localHtmlUrl);// 删除html格式合同

        System.out.println("PDF生成成功");
    }

    /**
     * @param templatePath
     * @param templateName 模板名字
     * @param htmUrl       生成的html地址
     * @param paramMap     数据
     * @throws Exception
     */
    private void htmHandler(String templatePath, String templateName,
                            String htmUrl, Map<String, Object> paramMap) throws Exception {

        configuration.setDefaultEncoding("UTF-8");
        configuration.setDirectoryForTemplateLoading(new File(templatePath));

        Template template = configuration.getTemplate(templateName);

        File outHtmFile = new File(htmUrl);

        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outHtmFile)));
        template.process(paramMap, out);

        out.close();
    }


    private static void pdfHandler(String htmUrl, String pdfUrl)
            throws DocumentException, IOException {
        File htmFile = new File(htmUrl);
        File pdfFile = new File(pdfUrl);

        String url = htmFile.toURI().toURL().toString();

        OutputStream os = new FileOutputStream(pdfFile);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);

        ITextFontResolver fontResolver = renderer
                .getFontResolver();
        // 解决中文支持问题
/*
		fontResolver.addFont(TEMPLATE+"仿宋.ttf",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
*/

        try {
            String fontDir = "E:\\project\\java\\freemarkerToPDF\\src\\main\\resources\\fonts";
            //添加字体库 begin
            File f = new File(fontDir);
            if (f.isDirectory()) {
                File[] files = f.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        String lower = name.toLowerCase();
                        return lower.endsWith(".otf") || lower.endsWith(".ttf") || lower.endsWith(".ttc");
                    }
                });
                for (int i = 0; i < files.length; i++) {
                    fontResolver.addFont(files[i].getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                }
            }
            //添加字体库end


            // SimSun    simFang
//            String path = CONTRACT+ "fonts\\" + "SimFang.ttf";
//            fontResolver.addFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
        }

        renderer.layout();
        try {
            renderer.createPDF(os);
        } catch (com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
        }
        os.close();
    }

    private static void deleteFile(String fileUrl) {
        File file = new File(fileUrl);
        file.delete();
    }

    /**
     * 获取生成Word文档所需要的数据
     */
    private static Map<String, Object> getWordData() {
        /*
         * 创建一个Map对象，将Word文档需要的数据都保存到该Map对象中
         */
        Map<String, Object> dataMap = new HashMap<>();

        /*
         * 直接在map里保存一个用户的各项信息
         * 该用户信息用于Word文档中FreeMarker普通文本处理
         * 模板文档占位符${name}中的name即指定使用这里的name属性的值"用户1"替换
         */
        ContractParticularsVo contractParticularsVo = new ContractParticularsVo();
        EnterpriseBaseInfo enterpriseBaseInfo = new EnterpriseBaseInfo();
        enterpriseBaseInfo.setEnterpriseName("测试甲方企业");
        enterpriseBaseInfo.setLegalPersonName("张三");
        enterpriseBaseInfo.setLegalPersonAddress("上海明哲路");
        enterpriseBaseInfo.setLegalPersonPhone("15562023564");

        contractParticularsVo.setEnterpriseBaseInfo(enterpriseBaseInfo);
        dataMap.put("enterpriseBaseInfo", enterpriseBaseInfo);
        EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
        enterpriseInfo.setEnterpriseName("测试乙方企业");
        enterpriseInfo.setEnterpriseLegalPerson("李四");
        enterpriseInfo.setDomicile("广东山策路");
        enterpriseInfo.setContact("15562356532");
        // 缺少设置乙方开户户名
        enterpriseInfo.setAccountName("测试乙方开户名称");
        enterpriseInfo.setBankAccount("132146546546");
        enterpriseInfo.setDepositBank("测试乙方开户行");
        dataMap.put("enterpriseInfo", enterpriseInfo);
        dataMap.put("loanAmount", "12342");
        dataMap.put("capitalLoanAmount", "一二三四");
        dataMap.put("timeLimit", "12");
        dataMap.put("termType", "月");
        dataMap.put("procedureInterestRate", "0.02");
        dataMap.put("partyBankAccount", "账号");

        return dataMap;
    }

    @Test
    public void test01() throws Exception {
        //		这里是选择某个模板
        String templateName = "201";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //这里是给具体的某些字段赋值
        paramMap.put("ZJHKZH", "271003********279975");
        paramMap.put("KYYE", "79244.95");
        paramMap.put("LXFS", "配置web.xml中LXFS属性，例如(张小凡，123,4567,8909)");
        paramMap.put("KHWD", "123");
        paramMap.put("CSKSRQ", "2016年10月31日00时00分");
        paramMap.put("KSRQ", "2017-03-14");
        paramMap.put("YE", "94444.95");
        paramMap.put("KHZH", "271**********07279975");
        paramMap.put("AH", "(2015)****字第0***0号");
        paramMap.put("CKH", "(2017)法YH****9控字第*号");
        paramMap.put("YDJAH", "(2015)***执字第00020号");
        paramMap.put("KZCS", "01");
        paramMap.put("XM", "秀不行不行的。哈哈哈哈！！");
        paramMap.put("FYMC", "****人民法院");
        paramMap.put("JSRQ", "2017-06-14");
        paramMap.put("KZZT", "1");
        paramMap.put("SE", "100");
        paramMap.put("LCZH", "987234234");
        paramMap.put("DATE", "2017年03月24日09时39分");
        paramMap.put("CKWH", "(2015)*****字第0**20-1**0号裁定书");
        paramMap.put("SKSE", "100");
        paramMap.put("CSJSRQ", "2016年10月31日 00时00分");
        paramMap.put("questionMainOrder", "ygc");
        paramMap.put("standardScore", "sunny");
        //调用具体的实现方法
        contractHandler(templateName, paramMap);
    }

}
