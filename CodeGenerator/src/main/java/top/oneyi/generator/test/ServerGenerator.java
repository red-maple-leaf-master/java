package top.oneyi.generator.test;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import top.oneyi.generator.utils.DbUtil;
import top.oneyi.generator.utils.Field;
import top.oneyi.generator.utils.FreemarkerUtil;

import java.io.File;
import java.util.*;


/**
 * @Description: 代码生成
 * @Author: wanyi
 * @Date: 2023/9/19
 */
public class ServerGenerator {

    public static void main(String[] args) throws Exception {
        String module = "generator";

        Map<String, Object> map = getData(module);

        Object bigDoMain = map.get("Domain");

        // 生成 dto
        FreemarkerUtil.initConfig("dto");
        String toDtoPath = "E:\\Desktop\\java_project\\one\\java\\CodeGenerator\\src\\main\\java\\top\\oneyi\\"+ module +"\\dto\\";
        FreemarkerUtil.generator(map, toDtoPath + bigDoMain + "Dto.java");

        // 生成 domain
        FreemarkerUtil.initConfig("domain");
        String toDomainPath = "E:\\Desktop\\java_project\\one\\java\\CodeGenerator\\src\\main\\java\\top\\oneyi\\"+ module +"\\domain\\";
        FreemarkerUtil.generator(map, toDomainPath + bigDoMain + ".java");

        // 生成 service
        FreemarkerUtil.initConfig("service");
        String toServicePath = "E:\\Desktop\\java_project\\one\\java\\CodeGenerator\\src\\main\\java\\top\\oneyi\\"+ module +"\\service\\";
        FreemarkerUtil.generator(map, toServicePath + bigDoMain + "Service.java");

        // 生成 serviceImpl
        FreemarkerUtil.initConfig("serviceImpl");
        String toServiceImplPath = "E:\\Desktop\\java_project\\one\\java\\CodeGenerator\\src\\main\\java\\top\\oneyi\\"+ module +"\\service\\impl\\";
        FreemarkerUtil.generator(map, toServiceImplPath + bigDoMain + "ServiceImpl.java");

        // 生成 controller
        FreemarkerUtil.initConfig("controller");
        String toControllerPath = "E:\\Desktop\\java_project\\one\\java\\CodeGenerator\\src\\main\\java\\top\\oneyi\\"+ module +"\\controller\\";
        FreemarkerUtil.generator(map, toControllerPath + bigDoMain + "Controller.java");

        // 生成 controller
        FreemarkerUtil.initConfig("mapper");
        String toMapperPath = "E:\\Desktop\\java_project\\one\\java\\CodeGenerator\\src\\main\\java\\top\\oneyi\\"+ module +"\\mapper\\";
        FreemarkerUtil.generator(map, toMapperPath + bigDoMain + "Mapper.java");
    }

    private static Map<String,Object> getData(String module) throws Exception {
         /*
            读 generatorConfig.xml
        */
        String generatorPath = "E:\\Desktop\\java_project\\one\\java\\CodeGenerator\\src\\main\\resources\\generator\\generatorConfig.xml";
        File file = new File(generatorPath);
        SAXReader reader = new SAXReader();
        Document dom = reader.read(file);
        /*
        获取文件的根节点
         */
        Element rootElement = dom.getRootElement();
        Element contextElement = rootElement.element("context");
        /*
        获取第一个 table 节点
         */
        Element tableElement = contextElement.elementIterator("table").next();
        /*
        获得大驼峰类名
         */
        String bigDoMain = tableElement.attributeValue("domainObjectName");
        /*
        获取小驼峰类名
         */
        String domain = bigDoMain.substring(0, 1).toLowerCase() + bigDoMain.substring(1);
        /*
        获取表名
         */
        String tableName = tableElement.attributeValue("tableName");
        /*
        获取表的中文名: 备注名
         */
        String tableNameCn = DbUtil.getTableComment(tableName);
        /*
        将表名打印到控制台
         */
        System.out.println("表名 = " + tableName);
        System.out.println("Domain = " + bigDoMain);

        /*
        获取数据库表的属性列表
         */
        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);

        /*
        获取要导入的Java包
         */
        Set<String> typeSet = getJavaTypes(fieldList);

        /*
        放到 Map 集合中供 freemarker 使用
         */
        Map<String, Object> map = new HashMap<>(10);
        map.put("Domain", bigDoMain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("module", module);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);
        return map;
    }

    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (Field field : fieldList) {
            set.add(field.getJavaType());
        }
        return set;
    }
}
