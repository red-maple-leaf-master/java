package top.oneyi.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MybatisTest {
    public static void main(String[] args) {
        Subject proxy = (Subject) Proxy
                .newProxyInstance(
                        Subject.class.getClassLoader(),
                        new Class[]{Subject.class},
                        new ProxyInvocationHandler());

        proxy.sayHello();
    }

}

interface Subject {
    String sayHello();
}

class ProxyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("       进入代理调用处理器 ");
        return "success";
    }
}