package top.oneyi.controller;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.oneyi.mapper.UserMapper;
import top.oneyi.pojo.User;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserMapper userMapper;

    /**
     *  更新的最后状态    ,isolation = Isolation.READ_COMMITTED
     */
    @GetMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public void update(){
        User byId = userMapper.selectById(1L);

        if(byId.getAge() == 1122){
            System.out.println("数据前置已被更新");
            System.out.println(byId.getAge());
            byId.setAge(22);
            userMapper.updateById(byId);
            System.out.println("我更新了Jone的年龄  22岁");
        }else{
            System.out.println(byId.getAge());
            System.out.println("请等mqtt消息消费完毕之后,在点击");
        }
    }

    /**
     * 模拟 mqtt 发送消息 阻塞 导致 更新状态比上一步晚
     */
    @Transactional(rollbackFor = Exception.class,isolation = Isolation.REPEATABLE_READ)
    @GetMapping("/update01")
    public void update01() throws InterruptedException {

        User byId = userMapper.selectById(1L);
        Thread.sleep(2000L);
        System.out.println(byId.getAge());
        if(byId.getAge() == 22){
            System.out.println("数据已被更新");
        }else{
            byId.setAge(1122);
            userMapper.updateById(byId);
            System.out.println("我更新了Jone的年龄  1122岁");
        }

    }
}
