package top.oneyi.generator.test;

import top.oneyi.generator.utils.FreemarkerUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/09/19/13:44
 */
public class demo {
    public static void main(String[] args) throws Exception {

        Map<String, Object> map = new HashMap<>(3);
        map.put("Domain", "Section");
        map.put("domain", "section");
        map.put("module", "generator");
        map.put("tableNameCn", "section");
//        map.put("fieldList", "属性列表");

        Object bigDoMain = map.get("Domain");
        Object module = map.get("module");

        // 生成 controller
        FreemarkerUtil.initConfig("controller");
        String toControllerPath = "E:\\Desktop\\java_project\\one\\java\\CodeGenerator\\src\\main\\java\\top\\oneyi\\"+ module+"\\controller\\";
        FreemarkerUtil.generator(map, toControllerPath + bigDoMain + "Controller.java");
    }

}
