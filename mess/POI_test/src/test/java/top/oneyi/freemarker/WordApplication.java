package top.oneyi.freemarker;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.oneyi.pojo.ContractParticularsVo;
import top.oneyi.pojo.EnterpriseBaseInfo;
import top.oneyi.pojo.EnterpriseInfo;
import top.oneyi.utils.freemarkerToWordUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Java使用FreeMarker生成Word文档主程序
 */
@SpringBootApplication
public class WordApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WordApplication.class, args);

        /**
         * 自动生成Word文档
         * 注意：生成的文档的后缀名需要为doc，而不能为docx，否则生成的Word文档会出错
         */
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name","小明");
        dataMap.put("age","12");
//        WordUtil.generateWord(getWordData(), "E:\\User.doc");
//        WordUtil.generateWord(getWordData(), "E:\\User.doc");
        freemarkerToWordUtil.generateWord(dataMap, "E:\\User.doc");
//        WordUtil.generateWord(dataMap, "E:\\User.xml");


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

        /**
         * 将用户的各项信息封装成对象，然后将对象保存在map中，
         * 该用户对象用于Word文档中FreeMarker表格和图片处理
         * 模板文档占位符${userObj.name}中的userObj即指定使用这里的userObj属性的值(即user2对象)替换
         */
/*        User user2 = new User();
        user2.setName("用户2");
        user2.setSex("女");
        user2.setBirthday("1992-02-02");
        // 使用FreeMarker在Word文档中生成图片时，需要将图片的内容转换成Base64编码的字符串
        user2.setPhoto(ImageUtil.getImageBase64String("E:/Word/Images/photo.jpg"));
        dataMap.put("userObj", user2);*/

        /*
         * 将多个用户对象封装成List集合，然后将集合保存在map中
         * 该用户集合用于Word文档中FreeMarker表单处理
         * 模板文档中使用<#list userList as user>循环遍历集合，即指定使用这里的userList属性的值(即userList集合)替换
         */
/*        List<User> userList = new ArrayList<>();
        User user3 = new User();
        user3.setName("用户3");
        user3.setSex("男");
        user3.setBirthday("1993-03-03");
        User user4 = new User();
        user4.setName("用户4");
        user4.setSex("女");
        user4.setBirthday("1994-04-04");
        userList.add(user3);
        userList.add(user4);
        dataMap.put("userList", userList);*/

        return dataMap;
    }
}

