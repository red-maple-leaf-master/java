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
        List<NormIndustry> father = normIndustryMapper.findByParentCode("0");
        for (NormIndustry firstNormIndustry : father) {
            List<NormIndustry> second = normIndustryMapper.findByParentCode(firstNormIndustry.getCode());
/*            for (NormIndustry secondNormIndustry : second) {
                List<NormIndustry> thirdParentCode = normIndustryMapper.findByParentCode(secondNormIndustry.getCode());
                secondNormIndustry.setNormIndustryList(thirdParentCode);
            }*/
            for (NormIndustry normIndustry : second) {
                normIndustry.setHasChild(0);
            }
            firstNormIndustry.setNormIndustryList(second);
        }

        NormIndustry normIndustry = father.get(0);
        System.out.println("normIndustry = " + normIndustry);

    }
}
