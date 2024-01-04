package top.oneyi.demo.proxy.CGLIBproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 *  CGLIB 动态代理
 *
 * @Author: wanyi
 * @Date: 2023/10/12/16:05
 */
public class CglibLogProxyInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println(" cglib dynamic proxy log begin ");
        Object result = methodProxy.invokeSuper(object, args);
        System.out.println(" cglib dynamic proxy log end ");
        return result;
    }

    /**
     * 动态创建代理
     *
     * @param cls 委托类
     * @return
     */
    public static <T> T createProxy(Class<T> cls) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setCallback(new CglibLogProxyInterceptor());
        return (T) enhancer.create();
    }
}
