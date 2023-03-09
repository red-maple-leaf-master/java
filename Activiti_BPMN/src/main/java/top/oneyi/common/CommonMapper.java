package top.oneyi.common;

import top.oneyi.pojo.ActBusinessStatus;

import java.util.List;

public interface CommonMapper<T> {

    ActBusinessStatus selectByProcessInstanceId(String processInstanceId);

}
