package top.oneyi.demo.warehourse.service;

import top.oneyi.demo.warehourse.Api.factoryApi;
import top.oneyi.demo.warehourse.service.impl.dayImpl;

public class day implements factoryApi {
    private dayImpl dayImpl = new dayImpl();

    @Override
    public String getStoreFee(String days, String Fee, String floorsDay) {
        return dayImpl.ttec();
    }
}
