package top.oneyi.service.Impl;

import org.springframework.stereotype.Service;
import top.oneyi.service.IPerson;

@Service
public class PersonImpl implements IPerson {
    @Override
    public void takeWash() {
        System.out.println("我备增强了");
    }
}
