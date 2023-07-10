package top.oneyi;


import cn.hutool.core.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.drools.core.util.StringUtils;
import org.drools.template.ObjectDataCompiler;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.oneyi.pojo.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class DroolsTest {


    @Resource
    private KieContainer kieContainer;

    @Test
    public void droolsPersonTest() {
        KieSession kieSession = kieContainer.newKieSession();
        Person person = new Person();

        person.setAge(55);
        person.setName("bob");

        kieSession.insert(person);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

    public List<BizDroolsRuleConfig> getList() {
        List<BizDroolsRuleConfig> list = new ArrayList<>();
        BizDroolsRuleConfig c1 = new BizDroolsRuleConfig();
        c1.setId("1254");
        c1.setRuleCode("dgdgd");
        c1.setRuleName("fix");
        c1.setMax("0");
        c1.setMin("300000");
        c1.setFixedFee("200.0");
        BizDroolsRuleConfig c2 = new BizDroolsRuleConfig();
        c2.setId("1255");
        c2.setRuleCode("efdfd");
        c2.setRuleName("fix");
        c2.setMax("300000");
        c2.setMin("500000");
        c2.setFixedFee("300.0");
        BizDroolsRuleConfig c3 = new BizDroolsRuleConfig();
        c3.setId("1256");
        c3.setRuleCode("dfdfd");
        c3.setRuleName("fix");
        c3.setMax("500000");
        c3.setMin("800000");
        c3.setFixedFee("400.0");
        list.add(c1);
        list.add(c2);
        list.add(c3);
        return list;
    }

    @Test
    public void test01() {
        // 输入的保证金金额
        BigDecimal bzjAmount = new BigDecimal("200");

        List<BizDroolsRuleConfig> list = this.getList();
        GuatanteeCost guatanteeCost = new GuatanteeCost();
        guatanteeCost.setAmount(bzjAmount.doubleValue());
        //创建模板填充对象
        ObjectDataCompiler converter = new ObjectDataCompiler();

        String drlContent = StringUtils.EMPTY;
        try (InputStream dis = ResourceFactory
                .newClassPathResource("rules/FixRateCostCalculatorRule.drt", CharsetUtil.UTF_8)
                .getInputStream()) {
            //填充模板内容
            drlContent = converter.compile(list, dis);
            log.info("生成的规则内容:{}", drlContent);
        } catch (IOException e) {
            log.error("获取规则模板文件出错:{}", e.getMessage());
        }

        KieHelper helper = new KieHelper();
        helper.addContent(drlContent, ResourceType.DRL);
        KieSession ks = helper.build().newKieSession();

        ks.insert(guatanteeCost);


//        kieSession.setGlobal("logger",log);
        int allRules = ks.fireAllRules();
        Double cost = guatanteeCost.getCost();
        log.info("成功执行{}条规则", allRules);
        log.info("计算保费{}元", cost);
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.dispose();

    }

    @Test
    public void test02() {
        KieSession kieSession = kieContainer.newKieSession();
        GradingLevel gradingLevel = new GradingLevel();
        gradingLevel.setScoreInterval(50);

        kieSession.insert(gradingLevel);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
