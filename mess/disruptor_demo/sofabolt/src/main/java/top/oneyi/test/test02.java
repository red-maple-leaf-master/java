package top.oneyi.test;

import com.alipay.sofa.jraft.rhea.client.DefaultRheaKVStore;
import com.alipay.sofa.jraft.rhea.client.RheaKVStore;
import com.alipay.sofa.jraft.rhea.options.PlacementDriverOptions;
import com.alipay.sofa.jraft.rhea.options.RegionRouteTableOptions;
import com.alipay.sofa.jraft.rhea.options.RheaKVStoreOptions;
import com.alipay.sofa.jraft.rhea.options.configured.MultiRegionRouteTableOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.PlacementDriverOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.RheaKVStoreOptionsConfigured;
import top.oneyi.unitls.KVStoreUtils;

import java.util.List;

public class test02 {
    public static void main(String[] args) {
        RheaKVStore rheaKVStore = KVStoreUtils.getRheaKVStore();


        String key = "test";

        byte[] bytes = rheaKVStore.bGet(key.getBytes());
        System.out.println(new String(bytes));


    }
}
