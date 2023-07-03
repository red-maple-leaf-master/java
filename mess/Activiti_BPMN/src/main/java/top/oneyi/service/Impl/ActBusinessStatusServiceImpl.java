package top.oneyi.service.Impl;

import org.springframework.stereotype.Service;
import top.oneyi.mapper.ActBusinessStatusMapper;
import top.oneyi.pojo.ActBusinessStatus;
import top.oneyi.service.ActBusinessStatusService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActBusinessStatusServiceImpl implements ActBusinessStatusService {


    @Override
    public List<ActBusinessStatus> list(ActBusinessStatus actBusinessStatus) {
        try {
            List<ActBusinessStatus> actBusinessStatuses = find("8bae4294-bd75-11ed-a38b-a036bc096aaf", ActBusinessStatus.class, ActBusinessStatusMapper.class);
            System.out.println("actBusinessStatus = " + actBusinessStatuses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public <T, V> List<T> find(String id, Class<T> type, Class<V> mapper) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        T po = type.newInstance();
        V dao = mapper.newInstance();
        Method processInstanceId = mapper.getDeclaredMethod("selectByProcessInstanceId", String.class);
        processInstanceId.setAccessible(true);
        po = (T) processInstanceId.invoke(dao, id);
        List<T> list = new ArrayList<>();
        list.add(po);
        return list;
    }
}
