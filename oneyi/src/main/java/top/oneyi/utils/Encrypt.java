package top.oneyi.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
/**
 * 加密算法
 * @author oneyi
 * @date 2023/3/9
 */

public class Encrypt {

    //高强度加密算法,不可逆
    public static String md5(String password, String salt){

        // param1 密码 param2 盐值  param3 加密次数
        return new Md5Hash(password,salt,2).toString();
    }



}
