package top.oneyi.demo.proxy.CGLIBproxy;

import top.oneyi.demo.proxy.entity.Service;
import top.oneyi.demo.proxy.entity.ServiceImpl;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/10/12/16:10
 */
public class cglibTest {
    public static void main(String[] args) {
        ServiceImpl proxy = CglibLogProxyInterceptor.createProxy(ServiceImpl.class);
        proxy.getPrint1();
        System.out.println("############");
        proxy.getPrint2();
    }
}
