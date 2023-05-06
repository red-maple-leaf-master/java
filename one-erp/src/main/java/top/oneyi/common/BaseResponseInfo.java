package top.oneyi.common;
/**
 * 通用返回类
 * @author oneyi
 * @date 2023/5/6
 */
public class BaseResponseInfo {
    public int code;
    public Object data;

    public BaseResponseInfo() {
        code = 400;
        data = null;
    }
}
