package top.oneyi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.mapper.NormIndustryMapper;
import top.oneyi.pojo.NormIndustry;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class NormIndustryTest {


    @Resource
    private NormIndustryMapper normIndustryMapper;

    @Test
    public void getTreeData(){
        List<NormIndustry> all = normIndustryMapper.findAll();
        System.out.println("all = " + all);

    }
}
