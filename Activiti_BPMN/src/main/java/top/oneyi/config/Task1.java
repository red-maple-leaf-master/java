package top.oneyi.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务练习
 * @author oneyi
 * @date 2023/3/21
 */

//@Component
//@EnableScheduling
public class Task1 {
    @Scheduled(cron ="*/1 * * * * ?")
    public void sayWord() {
        System.out.println("world");
    }


}
