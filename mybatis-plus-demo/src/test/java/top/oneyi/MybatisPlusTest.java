package top.oneyi;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.oneyi.mapper.UserMapper;
import top.oneyi.pojo.User;

import java.util.Collections;

@SpringBootTest
public class MybatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User(null, "李四", 23, "lisi@qteoc.com");
        int rows = userMapper.insert(user);
        System.out.println("受影响的行数" + rows);
        Long id = user.getId();
        System.out.println("生成的id为:" + id);  // 1626489795328679938

    }

    @Test
    public void testDeleteById() {
        int rows = userMapper.deleteById("1626489795328679938");
        System.out.println("受影响的行数" + rows);
    }

    @Test
    public void GeneratorTest() {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/oneyi? characterEncoding=utf-8&userSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("oneyi") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://mybatis_plus"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.oneyi") // 设置父包名
                            .moduleName("mybatisplus") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://mybatis_plus"));
                    // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker 引擎模板，默认的是Velocity引擎模板
                .execute();

    }


}