package top.oneyi;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.mapper.UserMapper;
import top.oneyi.pojo.User;
import top.oneyi.util.JsonUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = MybatisPlusDemo.class)//加载 SpringBoot 启动类
public class MybatisPlusTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        //selectList()根据MP内置的条件构造器查询一个list集合，null表示没有条件，即查询所有
        userMapper.selectList(null).forEach(System.out::println);
    }

    @Test
    public void SelectList() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        Page<User> page = new Page<>(1,5);
        List<OrderItem> strings = new ArrayList<>();
        strings.add(new OrderItem("age",false));
        page.addOrder(strings);
        IPage<User> all = userMapper.findAll(page,wrapper);
        for (User user : all.getRecords()) {
            System.out.println("user = " + user);
        }
        System.out.println("all.getTotal() = " + all.getTotal());
        System.out.println("all.getPages() = " + all.getPages());

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