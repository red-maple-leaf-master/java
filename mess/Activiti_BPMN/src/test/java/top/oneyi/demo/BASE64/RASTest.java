package top.oneyi.demo.BASE64;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import top.oneyi.pojo.Person;
import top.oneyi.util.RsaUtil;

/**
 * 非对称加密
 *
 * @author oneyi
 * @date 2023/5/9
 */
public class RASTest {


    @Test
    public void test01() {
        //字符串
        String str = "huanzi.qch@qq.com:欢子";
        try {
            System.out.println("私钥：" + RsaUtil.getPrivateKey());
            System.out.println("公钥：" + RsaUtil.getPublicKey());

            //公钥加密
            byte[] ciphertext = RsaUtil.encryptByPublicKey(str.getBytes(), RsaUtil.getPublicKey());
            //私钥解密
            byte[] plaintext = RsaUtil.decryptByPrivateKey(ciphertext, RsaUtil.getPrivateKey());

            System.out.println("公钥加密前：" + str);
            System.out.println("公钥加密后：" + Base64.encodeBase64String(ciphertext));
            System.out.println("私钥解密后：" + new String(plaintext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        //复杂对象
        Person pserson = new Person(1L, "aa", 14);
        pserson.setAge(12);
        pserson.setName("张三");
        try {
            System.out.println("私钥：" + RsaUtil.getPrivateKey());
            System.out.println("公钥：" + RsaUtil.getPublicKey());

            //公钥加密
            byte[] ciphertext = RsaUtil.encryptByPublicKey(pserson.toString().getBytes(), RsaUtil.getPublicKey());
            //私钥解密
            byte[] plaintext = RsaUtil.decryptByPrivateKey(ciphertext, RsaUtil.getPrivateKey());

            System.out.println("公钥加密前：" + pserson.toString());
            System.out.println("公钥加密后：" + Base64.encodeBase64String(ciphertext));
            System.out.println("私钥解密后：" + new String(plaintext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
