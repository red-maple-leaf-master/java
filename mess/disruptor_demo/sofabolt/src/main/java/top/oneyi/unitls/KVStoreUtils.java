package top.oneyi.unitls;

import com.alipay.sofa.jraft.rhea.client.DefaultRheaKVStore;
import com.alipay.sofa.jraft.rhea.client.RheaKVStore;
import com.alipay.sofa.jraft.rhea.options.PlacementDriverOptions;
import com.alipay.sofa.jraft.rhea.options.RegionRouteTableOptions;
import com.alipay.sofa.jraft.rhea.options.RheaKVStoreOptions;
import com.alipay.sofa.jraft.rhea.options.configured.MultiRegionRouteTableOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.PlacementDriverOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.RheaKVStoreOptionsConfigured;

import java.util.List;

public class KVStoreUtils {

    public static RheaKVStore getRheaKVStore(){
        String serverList = "127.0.0.1:8891,127.0.0.1:8892,127.0.0.1:8893,127.0.0.1:8894,127.0.0.1:8895";
        // 访问KVStore的客户端
        final RheaKVStore rheaKVStore = new DefaultRheaKVStore();

        final List<RegionRouteTableOptions> routeTableOptionsList = MultiRegionRouteTableOptionsConfigured
                .newConfigured()
                .withInitialServerList(-1L,serverList)
                .config();


        final PlacementDriverOptions pdOts = PlacementDriverOptionsConfigured.newConfigured()
                .withFake(true)
                .withRegionRouteTableOptionsList(routeTableOptionsList)
                .config();

        final RheaKVStoreOptions opts = RheaKVStoreOptionsConfigured.newConfigured()
                .withInitialServerList(serverList)
                .withPlacementDriverOptions(pdOts)
                .config();
        // 初始化客户端
        rheaKVStore.init(opts);

        return rheaKVStore;
    }
}
