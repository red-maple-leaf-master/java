//package top.oneyi.generator.utils;
//
//
//import freemarker.template.Configuration;
//import freemarker.template.DefaultObjectWrapper;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Map;
//
///**
// * Created with IntelliJ IDEA.
// * 代码生成器工具类
// * @Author: wanyi
// * @Date: 2023/09/19/11:33
// */
//public class FreemarkerUtil {
//
//    private static Template temp;
//
//    public static void initConfig(String ftlName) throws IOException {
//
//        Configuration conf = new Configuration(Configuration.VERSION_2_3_29);
//
//        String ftlPath = "E:\\Desktop\\java_project\\one\\java\\CodeGenerator\\src\\main\\java\\top\\oneyi\\generator\\ftl\\";
//
//        conf.setDirectoryForTemplateLoading(new File(ftlPath));
//
//        conf.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
//        temp = conf.getTemplate(ftlName + ".ftl");
//    }
//
//    public static void generator(Map<String, Object> map, String fileName) throws IOException, TemplateException {
//        FileWriter fw = new FileWriter(fileName);
//        BufferedWriter bw = new BufferedWriter(fw);
//        temp.process(map, bw);
//        bw.flush();
//        fw.close();
//    }
//}
