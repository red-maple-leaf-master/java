package top.oneyi.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author: xjh
 * @Date: 2019/5/19 8:59
 * @Description:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)

public class responseServer<T> implements Serializable {
    private Long id;
    /**
     * 状态码
     */
    private int code;
    /**
     * 描述
     */
    private String descp;
    /**
     * jwt 鉴权token
     */
    private String token;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String realName;
    /**
     * 首页路径
     */
    private String url;

    public Long getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }

    public String getToken() {
        return token;
    }

    public String getUserName() {
        return userName;
    }

    public String getRealName() {
        return realName;
    }

    public String getUrl() {
        return url;
    }

    private responseServer(int code, String descp, String token, Long id,String userName) {
        this.id = id;
        this.code = code;
        this.descp = descp;
        this.token = token;
        this.userName = userName;
    }

    private responseServer(int code, String descp, String token, Long id,String userName,String realName) {
        this.id = id;
        this.code = code;
        this.descp = descp;
        this.token = token;
        this.userName = userName;
        this.realName = realName;
    }

    private responseServer(int code, String descp, String token, Long id,String userName,String realName,String url) {
        this.id = id;
        this.code = code;
        this.descp = descp;
        this.token = token;
        this.userName = userName;
        this.realName = realName;
        this.url = url;
    }

    public static <T> responseServer<T> createByCodeMessage(int code, String descp, String token, Long id,String userName,String realName) {
        return new responseServer<>(code, descp, token, id ,userName,realName);
    }

    public static <T> responseServer<T> createByCodeMessageN(int code, String descp, String token, Long id,String userName,String realName,String url) {
        return new responseServer<>(code, descp, token, id ,userName,realName,url);
    }

}
