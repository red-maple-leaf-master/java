package top.oneyi.demo.warehourse.core;

import top.oneyi.demo.warehourse.Api.factoryApi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {
    private factoryApi cacheAdapter;

    public JDKInvocationHandler(factoryApi cacheAdapter) {
        this.cacheAdapter = cacheAdapter;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return factoryApi.class.getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args)).invoke(cacheAdapter, args);
    }
}