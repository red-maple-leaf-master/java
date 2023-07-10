package top.oneyi.demo.BASE64;

import org.junit.Test;
import top.oneyi.pojo.Person;
import top.oneyi.util.AesUtil;

public class AESTest {

    @Test
    public void test() {
        //16位
        String key = "MIGfMA0GCSqGSIb3";

        //字符串
        String str = "huanzi.qch@qq.com:欢子";
        try {
            //加密
            String encrypt = AesUtil.encrypt(str, key);
            //解密
            String decrypt = AesUtil.decrypt(encrypt, key);

            System.out.println("加密前：" + str);
            System.out.println("加密后：" + encrypt);
            System.out.println("解密后：" + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01() {
        //16位
        String key = "MIGfMA0GCSqGSIb3";

        //复杂对象
        Person pserson = new Person(1L, "aa", 14);
        pserson.setAge(12);
        pserson.setName("张三");
        try {
            //加密
            String encrypt = AesUtil.encrypt(pserson.toString(), key);
            //解密
            String decrypt = AesUtil.decrypt(encrypt, key);

            System.out.println("加密前：" + pserson.toString());
            System.out.println("加密后：" + encrypt);
            System.out.println("解密后：" + decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
