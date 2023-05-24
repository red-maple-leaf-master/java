package top.oneyi;



import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import top.oneyi.pojo.Person;

import javax.annotation.Resource;

@SpringBootTest
public class DroolsTest {


    @Resource
    private KieContainer kieContainer;


    @Test
    public void droolsPersonTest(){
        KieSession kieSession = kieContainer.newKieSession();
        Person person = new Person();

        person.setAge(55);
        person.setName("bob");

        kieSession.insert(person);
        kieSession.fireAllRules();
        kieSession.dispose();
    }

}
