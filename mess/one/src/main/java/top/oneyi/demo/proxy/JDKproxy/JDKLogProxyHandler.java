package top.oneyi.demo.proxy.JDKproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 *jdk动态代理
 * @Author: wanyi
 * @Date: 2023/10/12/15:50
 */
public class JDKLogProxyHandler implements InvocationHandler {

    private Object targetObject;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println(" jdk dynamic proxy log begin ");

        Object result = method.invoke(targetObject, args);

        System.out.println(" jdk dynamic proxy log end ");
        return result;
    }

    /**
     * 根据委托类动态产生代理类
     * @param targetObject 委托类对象
     * @return 代理类
     */
    public Object createPorxy(Object targetObject){
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader()
                ,targetObject.getClass().getInterfaces(),this);
    }
}
