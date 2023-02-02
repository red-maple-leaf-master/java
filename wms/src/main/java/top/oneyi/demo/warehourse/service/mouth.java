package top.oneyi.demo.warehourse.service;

import top.oneyi.demo.warehourse.Api.factoryApi;
import top.oneyi.demo.warehourse.service.impl.mouthImp;

public class mouth implements factoryApi {
    private mouthImp mouthImp = new mouthImp();
    @Override
    public String getStoreFee(String days, String Fee, String floorsDay) {
        return mouthImp.send();
    }
}
