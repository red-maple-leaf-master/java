package top.oneyi.unitls;


import com.google.common.collect.Lists;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class CmdContainer {

    // 饿汉式 单例
    private static CmdContainer instance = new CmdContainer();

    // 私有构造函数
    private CmdContainer() {
    }

    //对外开放获取实例方法
    public static CmdContainer getInstance() {
        return instance;
    }

    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    // 队列添加方法
    public boolean cache(String t) {
        return queue.offer(t);
    }

    //  获取队列的长度
    public int size() {
        return queue.size();
    }


    // 获取缓存的所有记录
    public List<String> getAll() {
        List<String> msgList = Lists.newArrayList();
        // drainTo  非常快的速度获取速度  还会清空队列  还是非阻塞的
        int count = queue.drainTo(msgList);
        return count == 0 ? null : msgList;
    }
}
