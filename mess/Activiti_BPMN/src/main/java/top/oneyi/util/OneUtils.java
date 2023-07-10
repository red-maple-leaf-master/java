package top.oneyi.util;

import org.springframework.stereotype.Component;
import top.oneyi.common.CommonMapper;
import top.oneyi.pojo.ActBusinessStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OneUtils {


    public List<String> findByProcessIds(CommonMapper mapper, List<String> processIds) {
        List<ActBusinessStatus> list = new ArrayList<>();
        for (String processId : processIds) {
            ActBusinessStatus actBusinessStatus = mapper.selectByProcessInstanceId(processId);
            list.add(actBusinessStatus);
        }
        List<String> ids = list.stream().map(ActBusinessStatus::getId).collect(Collectors.toList());
        return ids;
    }
}
