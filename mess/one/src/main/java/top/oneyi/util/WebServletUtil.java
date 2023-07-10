package top.oneyi.util;

import javax.servlet.http.HttpServletRequest;

/**
 * request工具
 *
 * @author oneyi
 * @date 2023/2/8
 */

public class WebServletUtil {

    /**
     * 获取访问者的ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        System.out.println("IP地址为************" + ip);
        return ip;
    }

}
