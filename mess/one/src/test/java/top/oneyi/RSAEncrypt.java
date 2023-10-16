package top.oneyi;

import org.junit.Test;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {

    final static Base64.Decoder decoder = Base64.getDecoder();
    final static Base64.Encoder encoder = Base64.getEncoder();

    /**
     * RSA公钥加密
     * @param str 加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception  加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = decoder.decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                .generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = encoder.encodeToString(cipher.doFinal(str
                .getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     * @param str 加密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //base64解码后的字符串
        byte[] inputByte =  decoder.decode(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded =  decoder.decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    //生成密钥对
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        //可以理解为：加密后的密文长度，实际原文要小些 越大 加密解密越慢
        keyGen.initialize(512);
        KeyPair keyPair = keyGen.generateKeyPair();
        return keyPair;
    }

    public static Map<Integer, String>  genKeyPair() throws Exception {
        Map<Integer, String> keyMap = new HashMap<Integer, String>();
        //生成公私钥对
        KeyPair keyPair = getKeyPair();
        PublicKey publicKey =  keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String publicKeyString = encoder.encodeToString(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = encoder.encodeToString(privateKey.getEncoded());
        // 将公钥和私钥保存到Map
        keyMap.put(0,publicKeyString);  //0表示公钥
        keyMap.put(1,privateKeyString);  //1表示私钥
        return keyMap;
    }
    /**
     * 测试RSA加解密
     */
    @Test
    public void test1() throws Exception {
        Map<Integer, String> keyMap = RSAEncrypt.genKeyPair();
        String content = "传智播客";
        System.out.println("随机生成的公钥为:" + keyMap.get(0));
        System.out.println("随机生成的私钥为:" + keyMap.get(1));
        String messageEn = RSAEncrypt.encrypt(content,keyMap.get(0));
        System.out.println("加密后的字符串为:" + messageEn);
        String messageDe = RSAEncrypt.decrypt(messageEn,keyMap.get(1));
        System.out.println("解密后的字符串为:" + messageDe);

    }
}

