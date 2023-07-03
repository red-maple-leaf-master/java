package top.oneyi.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class commonFindMethod implements InvocationHandler {

    private Object invokeProxy;

    private Object proxy(ClassLoader loader,
                       Class<?>[] interfaces,
                       InvocationHandler h) {
       return invokeProxy = Proxy.newProxyInstance(loader, interfaces, h);
    }

    public void findById(ClassLoader loader,
                         Class<?>[] interfaces,
                         InvocationHandler h,Class<?> inter) throws IllegalAccessException, InstantiationException {
        Object o = inter.newInstance();
        Object proxy = proxy(loader, interfaces, h);
    }

    /**
     * 代理方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return "success";
    }
}
