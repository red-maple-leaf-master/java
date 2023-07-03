package top.oneyi.node;

import com.alipay.sofa.jraft.rhea.LeaderStateListener;
import com.alipay.sofa.jraft.rhea.client.DefaultRheaKVStore;
import com.alipay.sofa.jraft.rhea.client.RheaKVStore;
import com.alipay.sofa.jraft.rhea.options.PlacementDriverOptions;
import com.alipay.sofa.jraft.rhea.options.RheaKVStoreOptions;
import com.alipay.sofa.jraft.rhea.options.StoreEngineOptions;
import com.alipay.sofa.jraft.rhea.options.configured.MemoryDBOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.PlacementDriverOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.RheaKVStoreOptionsConfigured;
import com.alipay.sofa.jraft.rhea.options.configured.StoreEngineOptionsConfigured;
import com.alipay.sofa.jraft.rhea.storage.StorageType;
import com.alipay.sofa.jraft.util.Endpoint;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;
@Slf4j
public class Node03 {

    private static final AtomicLong leaderTerm = new AtomicLong(-1);

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port=8893;

        String dataPath="d:/server03/";

        String serverList = "127.0.0.1:8891,127.0.0.1:8892,127.0.0.1:8893,127.0.0.1:8894,127.0.0.1:8895";

        final StoreEngineOptions storeEngineOptions = StoreEngineOptionsConfigured
                .newConfigured()
                .withStorageType(StorageType.Memory)// 俩种 方式 Memory 和 RocksDB  前者性能快  后者性能慢
                .withMemoryDBOptions(MemoryDBOptionsConfigured.newConfigured().config())
                .withRaftDataPath(dataPath)  // 相关日志输出目录
                .withServerAddress(new Endpoint(ip,port))  // 当前节点的ip和端口
                .config();
        // 管理多个集群  一个集群中有多个 实例 最后一定只有一个集群
        final PlacementDriverOptions pdOpts = PlacementDriverOptionsConfigured
                .newConfigured()
                .withFake(true)
                .config();
        // KV数据库的管理配置项
        final RheaKVStoreOptions opts = RheaKVStoreOptionsConfigured.newConfigured()
                .withInitialServerList(serverList)  // 设置集群集合
                .withStoreEngineOptions(storeEngineOptions) // 设置
                .withPlacementDriverOptions(pdOpts) // 设置
                .config();

        // 根据KV数据库的配置项 创建一个实例出来
        RheaKVStore rheaKVStore = new DefaultRheaKVStore();
        rheaKVStore.init(opts);

        // 监听主节点的变化   参数 1 默认 -1  参数2  监听者
        rheaKVStore.addLeaderStateListener(-1, new LeaderStateListener() {
            // 如果当前节点成为主节点 会调用该方法
            @Override
            public void onLeaderStart(long l) {
                log.info("==================================node become leader==================");
                leaderTerm.set(l);
            }

            @Override
            public void onLeaderStop(long l) {
                leaderTerm.set(-1);
            }
        });

    }
}
