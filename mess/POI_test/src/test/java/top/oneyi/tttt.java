package top.oneyi;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.oneyi.pojo.ContractParticularsVo;
import top.oneyi.pojo.EnterpriseBaseInfo;
import top.oneyi.pojo.EnterpriseInfo;
import top.oneyi.utils.freemarkerToWordUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@Slf4j
public class tttt {


    @Test
    public void createHtml() {
        String outputPath = "E:\\ssd\\gyl\\temp\\out0001.html";
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");

        // 设置FreeMarker生成Word文档所需要的模板的路径
//        configuration.setDirectoryForTemplateLoading(new File("E:\\project\\java\\POI_test\\src\\main\\resources\\template\\"));
        // 设置相对路径
        configuration.setTemplateLoader(new ClassTemplateLoader(freemarkerToWordUtil.class, "/template/"));
        Writer out = null;

        File file = new File(outputPath);
        try {
            // ZstServerBO bo=new ArrayList<>();
            // List<T> list = null; 这里是获取你要插入模版的数据

            configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
            Template template = configuration.getTemplate("redoneHTML.ftl");
            out = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            ;
            template.process(getWordData(), out);
            log.info("生成静态html,路径：{}", outputPath);

        } catch (Exception e) {
            log.error("生成html静态文件失败", e);

        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    log.error("流关闭失败", e);
                }
            }

        }

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

}
