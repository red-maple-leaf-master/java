package top.oneyi.util;

/**
 * 常用工具类
 *
 * @author oneyi
 * @date 2022/12/23
 */

public class OneUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return null == str || "".equals(str.trim());
    }

}
