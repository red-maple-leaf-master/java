package top.oneyi.TestNG.demo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class demo {

    @BeforeClass
    public void setUp() {
        System.out.println("我是前置方法");
    }

    @Test(groups = { "fast" })
    public void aFastTest() {
        System.out.println("Fast test");
    }

    @Test(groups = { "slow" })
    public void aSlowTest() {
        System.out.println("Slow test");
    }
}
