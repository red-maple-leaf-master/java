//package top.oneyi.generator.test;
//
//
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import top.oneyi.generator.utils.DbUtil;
//import top.oneyi.generator.utils.Field;
//import top.oneyi.generator.utils.FreemarkerUtil;
//import top.oneyi.generator.utils.OneUtil;
//
//import java.io.File;
//import java.util.*;
//
//
///**
// * @Description: 代码生成
// * @Author: wanyi
// * @Date: 2023/9/19
// */
//public class ServerGenerator {
//
//    public static void main(String[] args) throws Exception {
//        // 模块名
//        String module = "generator";
//
//        Map<String, Object> map = getData(module,"blade_role");
//
//        Object bigDoMain = map.get("Domain");
//
//        String path = ServerGenerator.class
//                            .getResource("/")
//                            .getPath()
//                            .replace("target/classes/","")
//                            .substring(1);
//        // 目录结构
//        String dir = "top\\oneyi\\";
//
//
//        // 生成 dto
//        FreemarkerUtil.initConfig("dto");
//        String toDtoPath = path+ "src\\main\\java\\"+ dir + module +"\\dto\\";
//        FreemarkerUtil.generator(map, toDtoPath + bigDoMain + "Dto.java");
//
//        // 生成 domain
//        FreemarkerUtil.initConfig("domain");
//        String toDomainPath = path+ "src\\main\\java\\"+ dir + module  +"\\domain\\";
//        FreemarkerUtil.generator(map, toDomainPath + bigDoMain + ".java");
//
//        // 生成 service
//        FreemarkerUtil.initConfig("service");
//        String toServicePath = path+ "src\\main\\java\\"+ dir + module  +"\\service\\";
//        FreemarkerUtil.generator(map, toServicePath + bigDoMain + "Service.java");
//
//        // 生成 serviceImpl
//        FreemarkerUtil.initConfig("serviceImpl");
//        String toServiceImplPath = path+ "src\\main\\java\\"+ dir + module  +"\\service\\impl\\";
//        FreemarkerUtil.generator(map, toServiceImplPath + bigDoMain + "ServiceImpl.java");
//
//        // 生成 controller
//        FreemarkerUtil.initConfig("controller");
//        String toControllerPath = path+ "src\\main\\java\\"+ dir + module  +"\\controller\\";
//        FreemarkerUtil.generator(map, toControllerPath + bigDoMain + "Controller.java");
//
//        // 生成 controller
//        FreemarkerUtil.initConfig("mapper");
//        String toMapperPath = path+ "src\\main\\java\\"+ dir + module  +"\\mapper\\";
//        FreemarkerUtil.generator(map, toMapperPath + bigDoMain + "Mapper.java");
//    }
//
//
//    /**
//     *  获取字段数据
//     * @param module 模块名
//     * @param tableName 表名
//     * @return 字段数据
//     * @throws Exception
//     */
//    private static Map<String,Object> getData(String module,String tableName) throws Exception {
//        /*
//        获取大驼峰类名
//         */
//        String bigDoMain = DbUtil.lineToBigHump(tableName);
//        /*
//        获取小驼峰类名
//         */
//        String domain = DbUtil.lineToHump(tableName);
//
//        /*
//        将表名打印到控制台
//         */
//        System.out.println("表名 = " + tableName);
//        System.out.println("Domain = " + bigDoMain);
//        System.out.println("domain = " + domain);
//        /**
//         * 表的中文注释
//         */
//        String tableNameCn = DbUtil.getTableComment(tableName);
//
//        /*
//        获取数据库表的属性列表
//         */
//        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);
//
//        /*
//        获取要导入的Java包
//         */
//        Set<String> typeSet = getJavaTypes(fieldList);
//
//        /*
//        放到 Map 集合中供 freemarker 使用
//         */
//        Map<String, Object> map = new HashMap<>(10);
//        map.put("Domain", bigDoMain);
//        map.put("domain", domain);
//        map.put("tableNameCn", tableNameCn);
//        map.put("module", module);
//        map.put("fieldList", fieldList);
//        map.put("typeSet", typeSet);
//        return map;
//    }
//
//    /**
//     * 将Java数据类型保存到 set 中
//     * @param fieldList 字段集合
//     * @return
//     */
//    private static Set<String> getJavaTypes(List<Field> fieldList) {
//        Set<String> set = new HashSet<>();
//        for (Field field : fieldList) {
//            set.add(field.getJavaType());
//        }
//        return set;
//    }
//}
