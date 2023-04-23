package top.oneyi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.oneyi.mapper.NormIndustryMapper;
import top.oneyi.pojo.NormIndustry;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)//当前类为 springBoot 的测试类
@SpringBootTest(classes = ActivtiSpringBootApplication.class)//加载 SpringBoot 启动类
public class NormIndustryTest {


    @Resource
    private NormIndustryMapper normIndustryMapper;

    @Test
    public void getTreeData(){
        List<NormIndustry> fater = normIndustryMapper.findByParentCode("0");
        for (NormIndustry normIndustry : fater) {
            List<NormIndustry> second = normIndustryMapper.findByParentCode(normIndustry.getCode());
            for (NormIndustry normIndustry02 : second) {
                List<NormIndustry> byParentCode = normIndustryMapper.findByParentCode(normIndustry02.getCode());
                normIndustry02.setNormIndustryList(byParentCode);
            }
            normIndustry.setNormIndustryList(second);
        }

        for (NormIndustry normIndustry : fater) {
            System.out.println(normIndustry);
        }

    }
}
