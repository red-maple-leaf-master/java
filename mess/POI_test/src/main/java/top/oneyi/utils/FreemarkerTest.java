package top.oneyi.utils;


import top.oneyi.pojo.ContractParticularsVo;
import top.oneyi.pojo.EnterpriseBaseInfo;
import top.oneyi.pojo.EnterpriseInfo;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FreemarkerTest {
    private static String document = "document.ftl";

    //outputStream 输出流可以自己定义 浏览器或者文件输出流
    public static void createDocx(Map dataMap, OutputStream outputStream) {
        ZipOutputStream zipout = null;
        try {
            /*//图片配置文件模板
            ByteArrayInputStream documentXmlRelsInput = FreeMarkUtils.getFreemarkerContentInputStream(dataMap, documentXmlRels);*/

            //内容模板
            ByteArrayInputStream documentInput = FreeMarkUtils.getFreemarkerContentInputStream(dataMap, document);
            //最初设计的模板
            //File docxFile = new File(WordUtils.class.getClassLoader().getResource(template).getPath());
            File docxFile = new File("E:\\Desktop\\one\\result\\会议纪要.zip");//换成自己的zip路径
            if (!docxFile.exists()) {
                docxFile.createNewFile();
            }
            ZipFile zipFile = new ZipFile(docxFile);
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
            zipout = new ZipOutputStream(outputStream);
            //开始覆盖文档------------------
            int len = -1;
            byte[] buffer = new byte[1024];
            while (zipEntrys.hasMoreElements()) {
                ZipEntry next = zipEntrys.nextElement();
                InputStream is = zipFile.getInputStream(next);
                if (next.toString().indexOf("media") < 0) {
                    zipout.putNextEntry(new ZipEntry(next.getName()));
                    if ("word/document.xml".equals(next.getName())) {//如果是word/document.xml由我们输入
                        if (documentInput != null) {
                            while ((len = documentInput.read(buffer)) != -1) {
                                zipout.write(buffer, 0, len);
                            }
                            documentInput.close();
                        }
                    } else {
                        while ((len = is.read(buffer)) != -1) {
                            zipout.write(buffer, 0, len);
                        }
                        is.close();
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("word导出失败:" + e.getStackTrace());
            //logger.error();
        } finally {
            if (zipout != null) {
                try {
                    zipout.close();
                } catch (IOException e) {
                    System.out.println("io异常");

                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println("io异常");
                }
            }
        }
    }

    public static void main(String arg[]) {
        //指定输出docx路径
        File outFile = new File("E:\\Desktop\\one\\test.docx");
        try {
            createDocx(getWordData(), new FileOutputStream(outFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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