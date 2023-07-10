package top.oneyi.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 动态定时任务配置类
 *
 * @author pan_junbiao
 **/
//@Configuration      //1.主要用于标记配置类，兼备Component的效果
//@EnableScheduling   //2.开启定时任务
public class DynamicScheduleConfigurer implements SchedulingConfigurer {

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("欢迎访问 pan_junbiao的博客: " + LocalDateTime.now().toLocalTime()), new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext triggerContext) {
                        Date date = new Date();
                        long time = date.getTime() + 2000;
                        triggerContext.lastScheduledExecutionTime();
                        // 返回执行事件
                        return new Date();

//                        return new CronTrigger(cron).nextExecutionTime(triggerContext);
                    }
                }
        );
    }
}