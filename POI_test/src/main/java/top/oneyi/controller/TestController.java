package top.oneyi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.oneyi.pojo.ContractParticularsVo;
import top.oneyi.pojo.EnterpriseBaseInfo;
import top.oneyi.pojo.EnterpriseInfo;
import top.oneyi.utils.WordUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {



    @CrossOrigin(value = "http://localhost:8080")
    @GetMapping("/getHtml")
    public String test() throws Exception {
        String file = "E:\\User.html";
        WordUtil.generateWord(getWordData(), file);

        // 获取HTML文件流
        StringBuilder htmlSb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file), StandardCharsets.UTF_8));
            while (br.ready()) {
                htmlSb.append(br.readLine());
            }
            br.close();
            // 删除临时文件
            //file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // HTML文件字符串
        // 返回经过清洁的html文本
        return htmlSb.toString();
    }
    /**
     * 获取生成Word文档所需要的数据
     */
    private static Map<String, Object> getWordData() {
        Map<String, Object> dataMap = new HashMap<>();

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
    /**
     * 接收文件
     * @param file
     */
    @GetMapping("/addFile")
    public void sendMultipartFile(MultipartFile file) throws IOException {
        System.out.println("file.getName() = " + file.getName()); // 就是参数的名字
        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename()); // 文件的全名
        System.out.println("file.getContentType() = " + file.getContentType()); // 文件类型
        System.out.println("file.getSize() = " + file.getSize()); // 文件字节数
        System.out.println("file.getBytes() = " + file.getBytes()); // 文件转换成 字节数组
        System.out.println("file.getInputStream() = " + file.getInputStream()); // 文件转换成 输入流
        //   transferTo是复制file文件到指定位置(比如D盘下的某个位置),不然程序执行完,文件就会消失,程序运行时,临时存储在temp这个文件夹中
        // 俩个参数 File file ; Path path
    }
    /**
     * 保存图片文件到本地
     * @param file
     */
    @GetMapping("/saveFile")
    public String  save(MultipartFile file) throws IOException {
//        String files = ClassUtils.getDefaultClassLoader().getResource("files").getPath();
        String files = TestController.class.getClassLoader().getResource("static").getPath();
        String filename = file.getOriginalFilename();
        String url_path = files + File.separator + "files"+ File.separator +filename;
        // 访问路径=静态资源路径+文件目录路径
        String visitPath = "static/files/" + filename;
        System.out.println("url_path = " + url_path);
        File saveFile = new File(url_path);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
        }

        return visitPath;
    }
}
