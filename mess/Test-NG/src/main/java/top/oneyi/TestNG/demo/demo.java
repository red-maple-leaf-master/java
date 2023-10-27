package top.oneyi.TestNG.demo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

public class demo {

    @BeforeClass
    public void setUp() {
        System.out.println("我是前置方法");
    }

    @Test(groups = {"fast", "ww"})
    public void aFastTest() {
        System.out.println("Fast test ww");
    }

    @Test(groups = {"slow", "ww"})
    public void aSlowTest() {
        System.out.println("Slow test ww");
    }

    @Test(groups = {"slow", "bb"})
    public void bSlowTest() {
        System.out.println("Slow test bb");
    }
    @Test(groups = {"fast", "bb"})
    public void bFastTest() {
        System.out.println("Slow test bb");
    }


    @Parameters({"first-name"})
    @Test(groups = {"bb"})
    public void testSingleString(String firstName){
        System.out.println("Invoked testString " + firstName);
        assert "Cedric".equals(firstName);
    }


    /**
     * 生成数据为下面的verifyData1 方法赋值 name 是唯一标识
     * @return
     */
    @DataProvider(name = "test1")
    public Object[][] createData1() {
        return new Object[][] {
                { "Cedric", 36},
                { "Anne", 37},
        };
    }

    @Test(dataProvider = "test1")
    public void verifyData1(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }
// 二维数组 转迭代器
/*    @DataProvider(name = "test2")
    public Iterator<Object[]> createData2() {
        Object[][] myObjects = new Object[][]{{"x"}, {"y"}};
        return Arrays.asList(myObjects).iterator();
    } */
// 一维数组  一维数组迭代器
//    @DataProvider(name = "test2")
//    public Object[] createData2() {
//        return new Object[]{"x", "y"};
//    }
//
//    @Test(dataProvider = "test2")
//    public void verifyData1(String n) {
//        System.out.println(n);
//    }
}
