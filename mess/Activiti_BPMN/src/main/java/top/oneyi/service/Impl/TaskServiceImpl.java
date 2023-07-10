package top.oneyi.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.oneyi.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Transactional
    @Override
    public String save(String msg) {


//        String mas = mas();
//        msg = mas + msg;
        return msg;
    }


    public String mas() {
        int i = 1 / 0;
        return "我是异常";
    }
}
