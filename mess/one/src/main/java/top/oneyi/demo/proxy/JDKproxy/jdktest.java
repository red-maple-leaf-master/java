package top.oneyi.demo.proxy.JDKproxy;

import top.oneyi.demo.proxy.entity.Service;
import top.oneyi.demo.proxy.entity.ServiceImpl;

/**
 * Created with IntelliJ IDEA.
 *
 * jdk的动态代理 只能代理接口
 *
 * @Author: wanyi
 * @Date: 2023/10/12/15:51
 */
public class jdktest {
    public static void main(String[] args) {
        JDKLogProxyHandler logProxyHandler = new JDKLogProxyHandler();
        Service porxyser = (Service) logProxyHandler.createPorxy(new ServiceImpl());
        porxyser.getPrint1();
        System.out.println("00000000000000000000000");
        porxyser.getPrint2();
    }
}
