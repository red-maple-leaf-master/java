package top.oneyi.demo.proxy.entity;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wanyi
 * @Date: 2023/10/12/15:53
 */
public class ServiceImpl implements Service{

    public void getPrint1(){
        System.out.println("我输出了一个数字");
    }

    public void getPrint2(){
        System.out.println("我输出了一百个数字");
    }
}
