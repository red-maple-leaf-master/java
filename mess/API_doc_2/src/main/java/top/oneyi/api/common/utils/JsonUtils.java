package top.oneyi.api.common.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Json工具类
 *
 * @author oneyi
 * @date 2023/4/27
 */
public class JsonUtils {
    /**
     * 格式化输出json字符串
     *
     * @param jsonObject
     * @return
     */
    public static String output(JSONObject jsonObject) {
        String resString = jsonObject.toString();
        StringBuilder jsonForMatStr = new StringBuilder();
        int level = 0;
        for (int index = 0; index < resString.length(); index++) {//将字符串中的字符逐个按行输出
            //获取s中的每个字符
            char c = resString.charAt(index);

            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c).append("\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c).append("\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr.toString();
    }

    /**
     * 输出tab符
     *
     * @param level
     * @return
     */
    private static String getLevelStr(int level) {
        StringBuilder levelStr = new StringBuilder();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
}
