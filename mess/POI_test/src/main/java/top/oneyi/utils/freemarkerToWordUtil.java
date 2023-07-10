package top.oneyi.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Word文档工具类
 */
public class freemarkerToWordUtil {

    /**
     * 使用FreeMarker自动生成Word文档
     *
     * @param dataMap  生成Word文档所需要的数据
     * @param fileName 生成Word文档的全路径名称
     */
    public static void generateWord(Map<String, Object> dataMap, String fileName) throws Exception {
        // 设置FreeMarker的版本和编码格式
        Configuration configuration = new Configuration(new Version("2.3.28"));
        configuration.setDefaultEncoding("UTF-8");

        // 设置FreeMarker生成Word文档所需要的模板的路径
//        configuration.setDirectoryForTemplateLoading(new File("E:\\Desktop\\java_project\\one\\java\\POI_test\\src\\main\\resources\\template\\"));
        // 设置相对路径
        configuration.setTemplateLoader(new ClassTemplateLoader(freemarkerToWordUtil.class, "/template/"));
        // 设置FreeMarker生成Word文档所需要的模板
        Template t = configuration.getTemplate("test.ftl", "UTF-8");
        // 创建一个Word文档的输出流
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8"));
        //FreeMarker使用Word模板和数据生成Word文档
        t.process(dataMap, out);
        out.flush();
        out.close();
    }

}
