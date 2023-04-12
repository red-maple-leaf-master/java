package top.oneyi.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import top.oneyi.annotation.UpdateDate;

import java.util.Date;

@Component
@Aspect
public class DressAction {

    /**
     * 前置通知
     */
    @Before("execution(* top.oneyi.service.IPerson.*(..))")
    public void undress(){
        System.out.println("我是前置通知");
    }

    /**
     * 后置通知
     */
    @After("execution(* top.oneyi.service.IPerson.*(..))")
    public void wearDress(){
        System.out.println("我是后置通知");
    }
    @AfterReturning(pointcut = "@annotation(updateDate)")
    public void update(JoinPoint joinPoint, UpdateDate updateDate){
        System.out.println("当前时间为:" + new Date());
    }
}
