package top.oneyi;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
/**
 * velocity 测试
 * @author oneyi
 * @date 2023/4/23
 */
public class VelocityTest {
    @Test
    public void test1() throws IOException {
        // 1,设置velocity的资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 2,初始化velocity引擎
        Velocity.init(prop);
        // 3,创建velocity容器
        VelocityContext context = new VelocityContext();
        // 设置数据
        context.put("name", "zhangsan");
        // 4,加载velocity模板文件
        Template template = Velocity.getTemplate("vms/01-quickstart.vm", "utf-8");
        // 5,合并数据到模板
        FileWriter fw = new FileWriter("E:\\Desktop\\one\\java\\one\\src\\main\\resources\\HTML\\01-quickstart.html");
        // 合并+写入
        template.merge(context, fw);
        // 6,释放资源
        fw.close();
    }
} 
