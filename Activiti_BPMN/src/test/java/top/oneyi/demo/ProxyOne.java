package top.oneyi.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyOne {
    // 需要被代理的接口
    public interface Subject {
        String sayHello();
    }

    // 需要被代理的类
    public static class SubjectImpl implements Subject {
        @Override
        public String sayHello() {
            System.out.println("我被调用了");
            return "sucess";
        }
    }

    public static class ProxyInvocationHandler implements InvocationHandler {
        private Object target;

        public ProxyInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("       进入代理调用处理器 ");
            return method.invoke(target, args);
        }
    }

}

class ProxyTest {
    public static void main(String[] args) {
        ProxyOne.Subject subject = new ProxyOne.SubjectImpl();

        ProxyOne.Subject proxy = (ProxyOne.Subject) Proxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                new ProxyOne.ProxyInvocationHandler(subject));

        proxy.sayHello();
    }
}
